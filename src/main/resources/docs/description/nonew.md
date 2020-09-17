# nonew

This option prohibits the use of constructor functions for side-effects.
Some people like to call constructor functions without assigning its
result to any variable:
    new MyConstructor();
There is no advantage in this approach over simply calling
`MyConstructor` since the object that the operator `new` creates isn't
used anywhere so you should generally avoid constructors like this one.
