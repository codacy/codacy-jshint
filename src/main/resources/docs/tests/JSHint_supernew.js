//#Patterns: JSHint_supernew

//#Info: JSHint_supernew
var singleton = new function() {
    var privateVar;

    this.publicMethod  = function () {}
    this.publicMethod2 = function () {}
};