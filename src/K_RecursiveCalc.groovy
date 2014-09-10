import groovy.transform.TailRecursive

class RecursiveCalc {
    @TailRecursive
    int accumulate(int n, int sum = 0) {
        (n == 0) ? sum : accumulate(n - 1, sum + n)
    }
}

new RecursiveCalc().with {
    assert accumulate(10) == 55
}
