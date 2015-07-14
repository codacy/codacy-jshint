//#Patterns: JSHint_noarg

function main() {

    //#Warn: JSHint_noarg
    var i = arguments.caller;

    //#Warn: JSHint_noarg
    var j = arguments.callee;

    return 'Hello, World!';
}

main();