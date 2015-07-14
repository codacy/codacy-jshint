//#Patterns: JSHint_bitwise, JSHint_curly

//#Warn: JSHint_bitwise
if(true == false & true)
//#Info: JSHint_curly
    console.log("cenas");

function cenas() {

    //#Warn: JSHint_bitwise
    var cenas = 1 & 2;

    //#Warn: JSHint_bitwise
    //#Info: JSHint_curly
    if(true | false) return 0;
}