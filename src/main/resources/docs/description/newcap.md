# newcap

This option requires you to capitalize names of constructor functions.
Capitalizing functions that are intended to be used with `new` operator
is just a convention that helps programmers to visually distinguish
constructor functions from other types of functions to help spot
mistakes when using `this`.
Not doing so won't break your code in any browsers or environments but
it will be a bit harder to figure out—by reading the code—if the
function was supposed to be used with or without new. And this is
important because when the function that was intended to be used with
`new` is used without it, `this` will point to the global object instead
of a new object.
@deprecated JSHint is limiting its scope to issues of code correctness.
            If you would like to enforce rules relating to code style,
            check out [the JSCS
            project](https://github.com/jscs-dev/node-jscs).
