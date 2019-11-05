
This option prohibits unnecessary clauses within `switch` statements, e.g.
```javascript
switch (x) {
  case 1:
  default:
    z();
}
```
While clauses like these are techincally valid, they do not effect program behavior and may indicate an erroneous refactoring.

[Source](https://jshint.com/docs/options/#leanswitch)