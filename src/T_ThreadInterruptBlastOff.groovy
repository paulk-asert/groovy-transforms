import groovy.transform.ThreadInterrupt

@ThreadInterrupt
class BlastOff2 {
    def log = []

    def countdown(n) {
        Thread.sleep 100
        log << n
        if (n == 0) log << 'ignition'
        else countdown(n - 1)
    }
}

def b = new BlastOff2()
def t1 = Thread.start {
    try {
        b.countdown(10)
    } catch(InterruptedException ignore) {
        b.log << 'aborted'
    }
}
sleep 570
t1.interrupt()
t1.join()
assert b.log.join(' ') == '10 9 8 7 6 aborted'
