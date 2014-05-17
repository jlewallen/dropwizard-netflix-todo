'use strict';
define(['text!./default-layout.html', 'lib/lodash'], function(template, _) {
	defaultLayoutController.$inject = ['$scope', '$state'];

	function defaultLayoutController($scope, $state) {
		$scope.$state = $state;

		$scope.inStates = function() {
			return _.any(arguments, $state.includes);
		};
	}

	return {
		name: 'default',
		abstract: true,
		controller: defaultLayoutController,
		template: template,
		resolve: {
			
		}
	};
});