# notypeof

This option suppresses warnings about invalid `typeof` operator values.
This operator has only [a limited set of possible return
values](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/typeof).
By default, JSHint warns when you compare its result with an invalid
value which often can be a typo.
    // 'fuction' instead of 'function'
    if (typeof a == "fuction") { // Invalid typeof value 'fuction'
      // ...
    }
Do not use this option unless you're absolutely sure you don't want
these checks.
