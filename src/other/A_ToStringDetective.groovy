package other

import groovy.transform.ToString

@ToString(
        ignoreNulls=true,
        excludes='lastName',
        includeNames=true,
        includePackage=false,
        includeFields=true)
class Detective {
    String firstName, lastName
    List clues
    private nemesis = 'Moriarty'
}

def d = new Detective(firstName: 'Sherlock', lastName: 'Holmes')
assert d.toString() == 'Detective(firstName:Sherlock, nemesis:Moriarty)'
