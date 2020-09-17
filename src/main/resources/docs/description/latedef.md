# latedef

This option prohibits the use of a variable before it was defined.
JavaScript has function scope only and, in addition to that, all variables
are always moved—or hoisted— to the top of the function. This behavior can
lead to some very nasty bugs and that's why it is safer to always use
variable only after they have been explicitly defined.
Setting this option to "nofunc" will allow function declarations to be
ignored.
For more in-depth understanding of scoping and hoisting in JavaScript,
read [JavaScript Scoping and
Hoisting](http://www.adequatelygood.com/2010/2/JavaScript-Scoping-and-Hoisting)
by Ben Cherry.
