import groovy.transform.Canonical

@Canonical
class Inventor {
    String firstName, lastName
}

def i1 = new Inventor('Thomas', 'Edison')
def i2 = new Inventor('Thomas')
assert i1 != i2
assert i1.firstName == i2.firstName
assert i1.toString() == 'Inventor(Thomas, Edison)'
