# boss

This option suppresses warnings about the use of assignments in cases
where comparisons are expected. More often than not, code like `if (a =
10) {}` is a typo. However, it can be useful in cases like this one:
    for (var i = 0, person; person = people[i]; i++) {}
You can silence this error on a per-use basis by surrounding the assignment
with parenthesis, such as:
    for (var i = 0, person; (person = people[i]); i++) {}
