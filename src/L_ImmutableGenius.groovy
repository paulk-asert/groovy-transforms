import groovy.transform.Immutable

import static groovy.test.GroovyAssert.shouldFail

@Immutable
class Genius {
    String firstName, lastName
}

def g1 = new Genius(firstName: 'Albert', lastName: "Einstein")
assert g1.toString() == 'Genius(Albert, Einstein)'

def g2 = new Genius('Leonardo', "da Vinci")
assert g2.firstName == 'Leonardo'

shouldFail(ReadOnlyPropertyException) {
    g2.lastName = 'DiCaprio'
}
