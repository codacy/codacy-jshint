//#Patterns: noarg

function main() {

    //#Warn: noarg
    var i = arguments.caller;

    //#Warn: noarg
    var j = arguments.callee;

    return 'Hello, World!';
}

main();