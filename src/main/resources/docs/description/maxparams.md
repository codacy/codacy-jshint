# maxparams

This option lets you set the max number of formal parameters allowed per
function:
    // jshint maxparams:3
    function login(request, onSuccess) {
      // ...
    }
    // JSHint: Too many parameters per function (4).
    function logout(request, isManual, whereAmI, onSuccess) {
      // ...
    }
