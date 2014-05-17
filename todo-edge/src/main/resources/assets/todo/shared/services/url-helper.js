'use strict';
define(['shared/modules/shared-module', 'lib/lodash', './simple-resource'], function(module) {
	module.factory("defaultResource", [ "simpleResource", function(simpleResource) {
		return simpleResource.newResource(".");
	}]);
});