//#Patterns: maxstatements: { "maxstatements": "10" }

function main() {

   var i = 2;

    i = myFunction();

    return 'Hello, World';
}

//#Warn: maxstatements
function myFunction(){

    var a = 0;
    a = 1;
    a = 2;
    a = 3;
    a = 4;
    a = 5;
    a = 6;
    a = 7;
    a = 8;
    a = 9;
    a = 10;

    return a;
}
