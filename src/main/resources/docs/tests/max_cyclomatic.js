//#Patterns: JSHint_maxcomplexity: { "maxcomplexity": "3" }

function main() {

    var i = myFunction(10);

    return 'Hello, World';
}

//#Warn: JSHint_maxcomplexity
function myFunction(a) {

    if(a == 1){
        return 1;
    }

    if(a == 2){
        return 2;
    }

    if(a == 3){
        return 3;
    }


    if(a > 3 && a < 10){
        return a == 5 ? 5 : 6;
    }


    return a;
}
