'use strict';
define(['lib/angular/angular',
	'lib/angular/angular-ui-router',
	'lib/angular/ui-bootstrap',
	'lib/angular/ui-date',
	'lib/angular/ui-utils',
	'lib/jquery-ui/jquery-ui',
	'shared/services/busy-status',
	'shared/modules/shared-module',
	'shared/modules/repositories-module',
	'shared/services/error-handling',
	'shared/services/url-helper',
	'shared/services/modal-helper',
	'shared/filters',
	'lib/less'
],
	function(angular) {
		return angular.module('todo', ['ui.router', 'ui.bootstrap', 'ui.date', 'ui.utils', 'todo.busy', 'todo.repos', 'todo.shared', 'todo.errors', 'todo.filters']);
	}
);