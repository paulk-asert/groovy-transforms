import groovy.transform.Sortable

@Sortable(includes = 'last,initial')
class Politician {
    String first
    Character initial
    String last

    String initials() { first[0] + initial + last[0] }
}

// @Sortable(includes = 'last,initial') class Politician { ... }

def politicians = [
    new Politician(first: 'Margaret', initial: 'H', last: 'Thatcher'),
    new Politician(first: 'George', initial: 'W', last: 'Bush')
]

politicians.with {
    assert sort()*.initials() == ['GWB', 'MHT']
    def comparator = Politician.comparatorByInitial()
    assert toSorted(comparator)*.initials() == ['MHT', 'GWB']
}
