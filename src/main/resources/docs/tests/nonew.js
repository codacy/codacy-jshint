//#Patterns: JSHint_nonew

function Foo(){
    return "bar";
}

function main() {

    //#Info: JSHint_nonew
    new Foo();

    return 'Hello, World!';
}

main();