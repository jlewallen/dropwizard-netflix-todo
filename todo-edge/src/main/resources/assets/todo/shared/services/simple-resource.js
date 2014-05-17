'use strict';
define(['shared/modules/repositories-module', 'lib/lodash'], function(module, _) {

	module.config(["$httpProvider", function($httpProvider) {
		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	}]);

	module.factory("simpleResource", ['$http', '$q', function($http, $q) {

		function addRouteIfMissing(params) {
			var firstParam = params[0];
			if (!_.isNumber(firstParam) && !_.isString(firstParam)) {
				params.unshift('');
			}
		}

		function doHttp(params) {
			if (_.isEmpty(params.config)) {
				params.config = {};
			}

			params.config.params = _.extend(params.requestParams || {}, { _format : 'json'});

			if (!_.isUndefined(params.route) && !_.isEmpty(params.route.toString())) {
				params.route = "/" + params.route;
			}

			if (!_.isEmpty(params.data)) {
				params.config.data = params.data;
			}

			params.config.method = params.action;

			var urlPromise = $q.all(_.map(_.flatten(params.resourceUrl), function(part) {
					if (_.isFunction(part)) {
						return $q.when(part());
					}
					return $q.when(part);
				})).then(function(parts) {
					return parts.join('/') + params.route;
				});

			return urlPromise.then(function(url) {
				return $http(_.extend({ url : url }, params.config));
			});
		}

		function resourceAction(action, resourceUrl, hasData) {
			return function() {
				var argumentsArray = _.toArray(arguments);
				addRouteIfMissing(argumentsArray);
				var argumentNames = hasData ? ['route', 'data', 'requestParams', 'config'] : ['route', 'requestParams', 'config'];
				var params = _.object(argumentNames, argumentsArray);
				params.action = action;
				params.resourceUrl = resourceUrl;
				return promiseData(doHttp(params));
			};
		}

		function promiseData(request) {
			var deferred = $q.defer();
			request.then(function(response) {
				deferred.resolve(response.data);
			}).catch(function(response) {
					deferred.reject(response);
				});
			return deferred.promise;
		}

		function newSubResource() {
			return newResource(_.toArray(arguments));
		}

		function newResource(urlParts) {
			var resource = _.partial(newSubResource, urlParts);
			return _.extend(resource, {
				get : resourceAction('GET', urlParts, false),
				delete : resourceAction('DELETE', urlParts, false),
				head : resourceAction('HEAD', urlParts, false),
				jsonp : resourceAction('JSONP', urlParts, false),
				post : resourceAction('POST', urlParts, true),
				put : resourceAction('PUT', urlParts, true)
			});
		}

		return {
			newResource : newResource
		};
	}]);
});