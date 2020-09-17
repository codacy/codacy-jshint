# multistr

This option suppresses warnings about multi-line strings. Multi-line
strings can be dangerous in JavaScript because all hell breaks loose if
you accidentally put a whitespace in between the escape character (`\`)
and a new line.
Note that even though this option allows correct multi-line strings, it
still warns about multi-line strings without escape characters or with
anything in between the escape character and a whitespace.
    // jshint multistr:true
    var text = "Hello\
    World"; // All good.
    text = "Hello
    World"; // Warning, no escape character.
    text = "Hello\
    World"; // Warning, there is a space after \
@deprecated JSHint is limiting its scope to issues of code correctness.
            If you would like to enforce rules relating to code style,
            check out [the JSCS
            project](https://github.com/jscs-dev/node-jscs).
