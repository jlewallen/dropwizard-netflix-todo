'use strict';
define(['lib/angular/angular', "text!./busy-dialog.html"], function(angular, template) {
	var module = angular.module('nc.busy', []);

	module.directive("ncVisibleIfBusy", function() {
		return {
			restrict: 'A',
			link: function($scope, el, attrs) {
				$scope.$watch("busy", function(busy) {
					if (busy) {
						el.removeClass('ng-hide');
					} else {
						el.addClass('ng-hide');
					}
				});
			}
		};
	});

	module.directive("ncDisabledIfBusy", function() {
		return {
			restrict: 'A',
			link: function($scope, el, attrs) {
				$scope.$watch("busy", function(busy) {
					el.attr("disabled", busy);
				});
			}
		};
	});

	module.factory("busy", ["$q", "$timeout", "$modal", "$rootScope", function($q, $timeout, $modal, $rootScope) {

		function showBusyDialog() {
			return $modal.open({
				template: template
			});
		}

		return {
			showBusyDialog: showBusyDialog,
			apoligizeForSlowness: function(promise) {
				var deferred = $q.defer();

				var timer = $timeout(function() {
					var dialog = showBusyDialog();
					promise.then(function() {
						dialog.close();
					});
				}, 1000);

				promise.then(function(data) {
					$timeout.cancel(timer);
					deferred.resolve(data);
				});

				$rootScope.$broadcast("event:long-operation-started", deferred.promise);

				return deferred.promise;
			}
		};
	}]);

	module.config(['$provide', '$httpProvider', function($provide, $httpProvider) {
		var outstandingRequests = 0;

		$provide.factory('httpInterceptor', ['$q', '$rootScope', function($q, $rootScope) {
			$rootScope.isBusy = function() {
				return $rootScope.busy;
			};

			return {
				'request': function(config) {
					$rootScope.busy = true;
					if (outstandingRequests >= 0) {
						outstandingRequests++;
					} else {
						outstandingRequests = 1;
					}
					return config || $q.when(config);
				},

				'requestError': function(rejection) {
					if (--outstandingRequests <= 0) {
						$rootScope.busy = false;
					}
					return $q.reject(rejection);
				},

				'response': function(response) {
					if (--outstandingRequests <= 0) {
						$rootScope.busy = false;
					}
					return response || $q.when(response);
				},

				'responseError': function(rejection) {
					if (--outstandingRequests <= 0) {
						$rootScope.busy = false;
					}
					return $q.reject(rejection);
				}
			};

		}]);

		$httpProvider.interceptors.push('httpInterceptor');
	}]);
});