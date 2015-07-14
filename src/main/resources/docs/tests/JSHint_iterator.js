//#Patterns: JSHint_iterator

//#Warn: JSHint_iterator
Range.prototype.__iterator__ = function(){
    return new RangeIterator(this);
};
