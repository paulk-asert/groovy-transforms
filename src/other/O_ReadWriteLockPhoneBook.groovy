package other
import groovy.transform.*

class PhoneBook2 {
    private final phoneNumbers = [:]

    @WithReadLock
    def getNumber(key) {
        phoneNumbers[key]
    }

    @WithWriteLock
    def addNumber(key, value) {
        phoneNumbers[key] = value
    }
}
