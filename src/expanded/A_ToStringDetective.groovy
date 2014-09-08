package expanded

class Detective {
    String firstName, lastName
    String toString() {
        def _result = new StringBuilder()
        _result.append('Detective(')
        _result.append(this.firstName)
        _result.append(', ')
        _result.append(this.lastName)
        _result.append(')')
        return _result.toString()
    }
}

def d = new Detective(firstName: 'Sherlock', lastName: 'Holmes')
assert d.toString() == 'Detective(Sherlock, Holmes)'
