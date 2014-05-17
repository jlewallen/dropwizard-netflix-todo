'use strict';
define(['text!./dashboard.html', 'layout/default-layout'], function(template, layout) {

	dashboardController.$inject = ['$scope', '$state', '$http'];

	function dashboardController($scope, $state, $http) {
		$scope.model = {};

		$scope.busy = false;
		$scope.allocated = null;

		$scope.rebalanceMissing = function() {
			return $scope.rebalance("allocations/rebalance/missing");
		};

		$scope.rebalanceAll = function() {
			return $scope.rebalance("allocations/rebalance/all");
		};

		$scope.rebalance = function(url) {
			$scope.busy = true;
			$http({
				method : 'POST',
				url : url,
				data : $.param({
					_format : 'json'
				}),
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				}
			}).success(function(model) {
					$scope.busy = false;
					$scope.allocated = model;
				});
		};
	}

	return {
		name : 'dashboard',
		url : '/dashboard',
		parent : layout,
		template : template,
		controller : dashboardController
	};
});