package other

class NoisySet extends HashSet {
    @Override
    boolean add(item) {
        println "adding $item"
        super.add(item)
    }

    @Override
    boolean addAll(Collection items) {
        items.each { println "adding $it" }
        super.addAll(items)
    }
}

new NoisySet().with{
    add(1)
    addAll([2, 3])
    assert size() == 3
}
