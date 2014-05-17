'use strict';
define(['lib/angular/angular', 'lib/lodash', 'text!./error-dialog.html'], function(angular, _, template) {
	var module = angular.module('todo.errors', []);

	function isStackTraceInteresting(line) {
		return !/^(NHibernate|System|Remotion|Castle)/.test(line);
	}

	errorDialogController.$inject = ['$scope', 'title', 'callback', 'response'];

	function errorDialogController($scope, title, callback, response) {
		$scope.title = title;
		$scope.error = response.data;
		$scope.errors = [];
		var error = $scope.error;
		/*
		while (_.isObject(error) && _.isString(error.message)) {
			$scope.errors.push(_.extend(error, {
				expanded : false,
				stackTrace : _.chain(error.stackTrace.split("\n")).map(function(line) {
					return line.replace(/\s+at\s+/, '');
				}).map(function(line) {
						return {
							interesting : isStackTraceInteresting(line),
							line : line
						};
					}).value()
			}));
			error = error.innerException;
		}
		$scope.errors[$scope.errors.length - 1].expanded = true;
		 */

		$scope.done = function() {
			callback.reject(response);
		};

		$scope.cancel = function() {
			callback.reject(response);
		};
	}

	module.factory("errors", ['$q', '$modal', function($q, $modal) {
		function display(response) {
			var deferred = $q.defer();
			var modal = $modal.open({
				template : template,
				windowClass : 'error-dialog',
				controller : errorDialogController,
				resolve : {
					callback : function() {
						return deferred;
					},
					response : function() {
						return response;
					},
					title : function() {
						return 'An error occured';
					}
				}
			});

			modal.result.catch(function() {
				deferred.reject(response);
			});

			deferred.promise.finally(function() {
				modal.close();
			});

			return deferred.promise;
		}

		return {
			display : display
		};
	}]);

	module.config(['$provide', '$httpProvider', function($provide, $httpProvider) {
		$provide.factory('errorsHttpInterceptor', ['$q', '$injector', function($q, $injector) {
			return {
				'response' : function(response) {
					if (response.status == 200 && _.isObject(response.data)) {
						/*
						 if (response.success === false) {
						 var errors = $injector.get("errors");
						 return errors.display(response);
						 }
						 */
					}
					return response || $q.when(response);
				},

				'responseError' : function(rejection) {
					if (rejection.status == 500) {
						var errors = $injector.get("errors");
						return errors.display(rejection);
					}
					else if (rejection.status == 404) {
						var $state = $injector.get("$state");
						$state.go("dashboard");
					}
					return $q.reject(rejection);
				}
			};
		}]);

		$httpProvider.interceptors.push('errorsHttpInterceptor');
	}]);
});