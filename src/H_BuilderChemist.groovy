import groovy.transform.builder.Builder

@Builder
class Chemist {
    String first
    String last
    int born
}

def builder = Chemist.builder()
def c = builder.first("Marie").last("Curie").born(1867).build()
assert c.first == "Marie"
assert c.last == "Curie"
assert c.born == 1867
