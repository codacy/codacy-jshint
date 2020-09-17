# unused

This option warns when you define and never use your variables. It is very
useful for general code cleanup, especially when used in addition to
`undef`.
    // jshint unused:true
    function test(a, b) {
      var c, d = 2;
      return a + d;
    }
    test(1, 2);
    // Line 3: 'b' was defined but never used.
    // Line 4: 'c' was defined but never used.
In addition to that, this option will warn you about unused global
variables declared via the `global` directive.
When set to `true`, unused parameters that are followed by a used
parameter will not produce warnings. This option can be set to `vars` to
only check for variables, not function parameters, or `strict` to check
all variables and parameters.
