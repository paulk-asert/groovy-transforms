import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Actor {
    String firstName, lastName
}
def a1 = new Actor(firstName: 'Ian', lastName: 'McKellen')
def a2 = new Actor(firstName: 'Ian', lastName: 'McKellen')
assert !(a1.is(a2))
assert a1 == a2
