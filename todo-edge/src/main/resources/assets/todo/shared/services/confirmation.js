'use strict';
define(['shared/modules/shared-module', 'lib/lodash', 'text!./confirmation.html'], function(module, _, template) {
	module.factory("confirmation", ['$modal', '$q', function($modal, $q) {
		confirmController.$inject = ['$scope', 'title', 'callback'];

		function confirmController($scope, title, callback) {
			var model = $scope.model = {
				title: title
			};

			$scope.done = function() {
				callback.resolve($scope.selected);
			};

			$scope.cancel = function() {
				callback.reject();
			};
		}

		function confirm() {
			var deferred = $q.defer();
			var modal = $modal.open({
				template: template,
				controller: confirmController,
				resolve: {
					callback: function() {
						return deferred;
					},
					title: function() {
						return 'Confirm';
					}
				}
			});

			modal.result.catch(function() {
				deferred.reject();
			});

			deferred.promise.finally(function() {
				modal.close();
			});

			return deferred.promise;
		}

		return {
			confirm: confirm
		};
	}]);
});