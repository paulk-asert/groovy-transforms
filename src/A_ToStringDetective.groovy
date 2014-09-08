@groovy.transform.ToString
class Detective {
    String firstName, lastName
}

def d = new Detective(firstName: 'Sherlock', lastName: 'Holmes')
assert d.toString() == 'Detective(Sherlock, Holmes)'
