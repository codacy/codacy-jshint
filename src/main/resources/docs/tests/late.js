//#Patterns: latedef

function main() {

    i = 2;
    //#Warn: latedef
    var i;

    return 'Hello, World!';
}

main();