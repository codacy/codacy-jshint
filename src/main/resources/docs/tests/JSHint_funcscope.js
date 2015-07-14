//#Patterns: JSHint_funcscope

function test() {
    if (true) {
        var x = 0;
    }

//#Warn: JSHint_funcscope
    x += 1; // Default: 'x' used out of scope.
}