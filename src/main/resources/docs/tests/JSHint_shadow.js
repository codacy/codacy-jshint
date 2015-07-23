//#Patterns: shadow

function b() {
    var a = 2; //Shadow of variable a
    if(true) {
//#Warn: shadow
        var a = 3;
    }
}