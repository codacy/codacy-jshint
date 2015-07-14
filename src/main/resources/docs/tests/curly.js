//#Patterns: JSHint_curly
function main() {
    return 'Hello, World!';
}

function _xpto(a, b) {
    var not_camel_case;
    var camelCase;

    if (1 == 2) {
        return a
    }

    for(i = 1; i < 5; i+=1)
        //#Info: JSHint_curly
        console.log("blah");
    1+1;

//#Info: JSHint_curly
if (camelCase != not_camel_case) return a

}

//#Info: JSHint_curly
while(1) break;

main();