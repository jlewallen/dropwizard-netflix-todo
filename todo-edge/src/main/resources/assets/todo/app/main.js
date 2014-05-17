require.config({
	baseUrl : "/assets/todo",
	paths : {
		'text' : 'lib/text',
		"moment" : "lib/moment"
	},
	shim : {
		'test-util' : ['angular-matchers'],
		'lib/angular/angular' : { deps : ['lib/jquery'], exports : 'angular' },
		'lib/angular/angular-ui-router' : ['lib/angular/angular'],
		'lib/angular/ui-bootstrap' : ['lib/angular/angular'],
		'lib/angular/ui-date' : ['lib/angular/angular'],
		'lib/angular/ui-utils' : ['lib/angular/angular'],
		'lib/angular/angular-mocks' : ['lib/angular/angular'],
		'lib/jquery-ui/jquery-ui' : ['lib/jquery'],
		'lib/sprintf' : {
			exports : 'sprintf'
		}
	}
});

require(["app/angular-start"], function() {
});