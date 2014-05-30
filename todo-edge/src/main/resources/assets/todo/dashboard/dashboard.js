'use strict';
define(['text!./dashboard.html', 'layout/default-layout', 'app/angular-module'], function(template, layout, module) {

	module.service("todos", ['$http', '$q', function($http, $q) {
		return {
			getAll : function() {
				var defer = $q.defer();
				$http({
					method : 'GET',
					url : '/todos'
				}).success(function(response) {
					defer.resolve(response);
				});
				return defer.promise;
			},
			bulkAdd : function(number) {
				var defer = $q.defer();
				$http({
					method : 'POST',
					url : '/todos/bulk-add/' + number
				}).success(function(response) {
					defer.resolve(response);
				});
				return defer.promise;
			},
			add : function(todo) {
				var defer = $q.defer();
				$http({
					method : 'POST',
					url : '/todos',
					data : todo
				}).success(function(response) {
					defer.resolve(response);
				});
				return defer.promise;
			}
		}
	}]);

	dashboardController.$inject = ['$scope', '$state', 'todos'];

	function dashboardController($scope, $state, todos) {
		$scope.todos = [];
		$scope.newTodo = {};
		$scope.bulkAddNumber = 100;

		function refresh() {
			todos.getAll().then(function(todos) {
				$scope.todos = todos;
			});
		}

		refresh();

		$scope.bulkAddTodo = function() {
			todos.bulkAdd($scope.bulkAddNumber).then(refresh);
		};

		$scope.addTodo = function() {
			todos.add($scope.newTodo).then(refresh);
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