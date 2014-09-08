package expanded

import org.codehaus.groovy.util.HashCodeHelper

class Actor {
    String firstName, lastName
    int hashCode() {
        def _result = HashCodeHelper.initHash()
        _result = HashCodeHelper.updateHash(_result, this.firstName)
        _result = HashCodeHelper.updateHash(_result, this.lastName)
        return _result
    }

    boolean canEqual(other) {
        return other instanceof Actor
    }

    boolean equals(other) {
        if (other == null) return false
        if (this.is(other)) return true
        if (!( other instanceof Actor)) return false
        Actor otherTyped = (Actor) other
        if (!(otherTyped.canEqual(this))) return false
        if (!(this.getFirstName().is(otherTyped.getFirstName()))) {
            if (!(this.getFirstName() == otherTyped.getFirstName())) {
                return false
            }
        }
        if (!(this.getLastName().is(otherTyped.getLastName()))) {
            if (!(this.getLastName() == otherTyped.getLastName())) {
                return false
            }
        }
        return true
    }
}

def a1 = new Actor(firstName:'Ian', lastName: 'McKellen')
def a2 = new Actor(firstName:'Ian', lastName: 'McKellen')
assert !(a1.is(a2))
assert a1 == a2
