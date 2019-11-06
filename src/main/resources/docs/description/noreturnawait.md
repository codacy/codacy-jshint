Async functions resolve on their return value. In most cases, this makes returning the result of an AwaitExpression (which is itself a Promise instance) unnecessary. For clarity, it\'s often preferable to return the result of the asynchronous operation directly. The notable exception is within the `try` clause of a TryStatement--for more, see "await vs return vs return await":

<https://jakearchibald.com/2017/await-vs-return-vs-return-await/>

[Source](http://www.jshint.com/docs/options/#noreturnawait)
