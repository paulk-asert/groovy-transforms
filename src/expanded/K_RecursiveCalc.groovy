package expanded

class RecursiveCalc {
    int accumulate(int n, int sum) {
        int _sum_ = sum
        int _n_ = n
        while (true) {
            if (_n_ == 0) {
                return _sum_
            } else {
                int __n__ = _n_
                int __sum__ = _sum_
                _n_ = __n__ - 1
                _sum_ = __sum__ + __n__
            }
        }
    }

    int accumulate(int n) { accumulate(n, 0) }
}

new RecursiveCalc().with {
    assert accumulate(10) == 55
}
