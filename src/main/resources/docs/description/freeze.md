# freeze

This options prohibits overwriting prototypes of native objects such as
`Array`, `Date` and so on.
    // jshint freeze:true
    Array.prototype.count = function (value) { return 4; };
    // -> Warning: Extending prototype of native object: 'Array'.
