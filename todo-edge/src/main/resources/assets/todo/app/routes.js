'use strict';
define(function(require) {
	var mainModule = require('app/angular-module');
	mainModule.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider) {
		function registerControllers(controllers) {
			if (!_.isArray(controllers)) {
				controllers = [controllers];
			}
			_(controllers).forEach(function(controller) {
				$stateProvider.state(controller);
			});
		}

		$urlRouterProvider.otherwise("/dashboard");

		registerControllers(require('layout/default-layout'));
		registerControllers(require('dashboard/dashboard'));
	}]);
});