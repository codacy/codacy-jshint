//#Patterns: JSHint_shadow

function b() {
    var a = 2; //Shadow of variable a
    if(true) {
//#Warn: JSHint_shadow
        var a = 3;
    }
}