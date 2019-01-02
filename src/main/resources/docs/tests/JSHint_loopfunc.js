//#Patterns: loopfunc

for (var i = 0; i < 10; i++) {
//#Warn: loopfunc
    (function (i) {
        nums[i] = function (j) {
            return i + j;
        };
    }(i));
}
