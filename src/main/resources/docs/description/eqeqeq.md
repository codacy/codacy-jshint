# eqeqeq

This options prohibits the use of `==` and `!=` in favor of `===` and
`!==`. The former try to coerce values before comparing them which can
lead to some unexpected results. The latter don't do any coercion so
they are generally safer. If you would like to learn more about type
coercion in JavaScript, we recommend [Truth, Equality and
JavaScript](http://javascriptweblog.wordpress.com/2011/02/07/truth-equality-and-javascript/)
by Angus Croll.
