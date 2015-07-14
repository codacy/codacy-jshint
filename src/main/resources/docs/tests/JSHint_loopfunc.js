//#Patterns: JSHint_loopfunc

for (var i = 0; i < 10; i++) {
    (function (i) {
        nums[i] = function (j) {
            return i + j;
        };
//#Warn: JSHint_loopfunc
    }(i));
}