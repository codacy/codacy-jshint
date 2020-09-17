# maxstatements

This option lets you set the max number of statements allowed per function:
    // jshint maxstatements:4
    function main() {
      var i = 0;
      var j = 0;
      // Function declarations count as one statement. Their bodies
      // don't get taken into account for the outer function.
      function inner() {
        var i2 = 1;
        var j2 = 1;
        return i2 + j2;
      }
      j = i + j;
      return j; // JSHint: Too many statements per function. (5)
    }
