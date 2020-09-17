# forin

This option requires all `for in` loops to filter object's items. The
for in statement allows for looping through the names of all of the
properties of an object including those inherited through the prototype
chain. This behavior can lead to unexpected items in your object so it
is generally safer to always filter inherited properties out as shown in
the example:
    for (key in obj) {
      if (obj.hasOwnProperty(key)) {
        // We are sure that obj[key] belongs to the object and was not inherited.
      }
    }
For more in-depth understanding of `for in` loops in JavaScript, read
[Exploring JavaScript for-in
loops](http://javascriptweblog.wordpress.com/2011/01/04/exploring-javascript-for-in-l
by Angus Croll.
