import groovy.transform.TupleConstructor

@TupleConstructor
class Athlete {
    String firstName, lastName
}
def a1 = new Athlete('Michael', 'Jordan')
def a2 = new Athlete('Michael')
def a3 = new Athlete(firstName: 'Michael')
assert a1.firstName == a2.firstName
assert a2.firstName == a3.firstName
