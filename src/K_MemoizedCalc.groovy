import groovy.transform.Memoized

class Calc {
  def log = []

  @Memoized
  int sum(int a, int b) {
    log << "$a+$b"
    a + b
  }
}

new Calc().with {
  assert sum(3, 4) == 7
  assert sum(4, 4) == 8
  assert sum(3, 4) == 7
  assert log.join(' ') == '3+4 4+4'
}
