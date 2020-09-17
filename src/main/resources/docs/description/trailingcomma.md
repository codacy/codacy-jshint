# trailingcomma

This option warns when a comma is not placed after the last element in an
array or object literal. Due to bugs in old versions of IE, trailing
commas used to be discouraged, but since ES5 their semantics were
standardized. (See
[#11.1.4](http://www.ecma-international.org/ecma-262/5.1/#sec-11.1.4) and
[#11.1.5](http://www.ecma-international.org/ecma-262/5.1/#sec-11.1.5).)
Now, they help to prevent the same [visual
ambiguities](http://www.ecma-international.org/ecma-262/5.1/#sec-7.9.2)
that the strict usage of semicolons helps prevent.
For example, this code might have worked last Tuesday:
    [
        b + c
    ].forEach(print);
But if one adds an element to the array and forgets to compensate for the
missing comma, no syntax error is thrown, and a linter cannot determine
if this was a mistake or an intentional function invocation.
    [
        b + c
        (d + e)
    ].forEach(print);
If one always appends a list item with a comma, this ambiguity cannot
occur:
    [
        b + c,
    ].forEach(print);
    [
        b + c,
        (d + e),
    ].forEach(print);
