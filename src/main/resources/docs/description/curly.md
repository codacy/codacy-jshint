# curly

This option requires you to always put curly braces around blocks in
loops and conditionals. JavaScript allows you to omit curly braces when
the block consists of only one statement, for example:

```javascript
    while (day)
      shuffle();
```

However, in some circumstances, it can lead to bugs (you'd think that
`sleep()` is a part of the loop while in reality it is not):

```javascript
    while (day)
      shuffle();
      sleep();
```