# funcscope

This option suppresses warnings about declaring variables inside
of control structures while accessing them later from the outside.
Even though identifiers declared with `var` have two real scopes—global
and function—such practice leads to confusion among people new to
the language and hard-to-debug bugs. This is why, by default, JSHint
warns about variables that are used outside of their intended scope.
    function test() {
      if (true) {
        var x = 0;
      }
      x += 1; // Default: 'x' used out of scope.
                // No warning when funcscope:true
    }