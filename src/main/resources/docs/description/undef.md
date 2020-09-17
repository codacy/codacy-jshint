# undef

This option prohibits the use of explicitly undeclared variables. This
option is very useful for spotting leaking and mistyped variables.
    // jshint undef:true
    function test() {
      var myVar = 'Hello, World';
      console.log(myvar); // Oops, typoed here. JSHint with undef will complain
    }
If your variable is defined in another file, you can use the `global`
directive to tell JSHint about it.
