//#Patterns: funcscope

function test() {
    if (true) {
        var x = 0;
    }

//#Warn: funcscope
    x += 1; // Default: 'x' used out of scope.
}