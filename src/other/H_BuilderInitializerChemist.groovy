package other

import groovy.transform.*
import groovy.transform.builder.*

@Builder(builderStrategy=InitializerStrategy)
@Immutable
class Chemist {
    String first
    String last
    int born
}

@CompileStatic
def solution() {
    def init = Chemist.createInitializer().first("Marie").last("Curie").born(1867)
    new Chemist(init).with {
        assert first == "Marie"
        assert last == "Curie"
        assert born == 1867
    }
}

solution()
