//#Patterns: JSHint_eqeqeq
function main() {
    return 'Hello, World!';
}

function _xpto(a, b) {
    var not_camel_case;
    var camelCase;

    //#Warn: JSHint_eqeqeq
    if (1 == 2) {
        return a
    }

    //#Warn: JSHint_eqeqeq
    if (camelCase != not_camel_case) {
        return a
    }

}

main();