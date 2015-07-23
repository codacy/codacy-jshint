//#Patterns: iterator

//#Warn: iterator
Range.prototype.__iterator__ = function(){
    return new RangeIterator(this);
};
