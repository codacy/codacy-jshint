# singleGroups

This option prohibits the use of the grouping operator when it is not
strictly required. Such usage commonly reflects a misunderstanding of
unary operators, for example:
    // jshint singleGroups: true
    delete(obj.attr); // Warning: Unnecessary grouping operator.
