//#Patterns: bitwise, curly

//#Warn: bitwise
if(true == false & true)
//#Info: curly
    console.log("cenas");

function cenas() {

    //#Warn: bitwise
    var cenas = 1 & 2;

    //#Warn: bitwise
    //#Info: curly
    if(true | false) return 0;
}