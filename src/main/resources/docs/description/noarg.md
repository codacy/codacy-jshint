# noarg

This option prohibits the use of `arguments.caller` and
`arguments.callee`.  Both `.caller` and `.callee` make quite a few
optimizations impossible so they were deprecated in future versions of
JavaScript. In fact, ECMAScript 5 forbids the use of `arguments.callee`
in strict mode.
