//#Patterns: JSHint_latedef

function main() {

    i = 2;
    //#Warn: JSHint_latedef
    var i;

    return 'Hello, World!';
}

main();