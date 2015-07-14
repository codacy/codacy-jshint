//->Patterns: JSHint_maxdepth

function main(meaning) {
    var day = true;

    if (meaning === 42) {
        while (day) {
            shuffle();
            if (tired) {
//->Warn: JSHint_maxdepth
                while (false) {
                    sleep();
                }
            }
        }
    }

}