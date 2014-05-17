'use strict';
define(['lib/angular/angular', 'lib/lodash'], function(angular, _) {
	var module = angular.module('todo.filters', []);

	module.filter('joinBy', function() {
		return function(input, delimiter) {
			return (input || []).join(delimiter || ', ');
		};
	});

	module.filter('startFrom', function() {
		return function(input, start) {
			if (input) {
				start = _.parseInt(start);
				return input.slice(start);
			}
			return input;
		};
	});

	return module;
});