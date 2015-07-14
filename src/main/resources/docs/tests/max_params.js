//#Patterns: JSHint_maxparams: { "maxparams": "7" }

function main() {

  i = 2;

  var i = myFunction(1, 2, 3, 4, 5, 6, 7, 8);

  return 'Hello, World';
}

//#Warn: JSHint_maxparams
function myFunction(a, b, c, d, e, f, g, h) {

  return a + b + c + d + e + f + g + h;

}
