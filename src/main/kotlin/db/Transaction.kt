package db

class Transaction {

    // name, value
    private val initialStateNameToValue: MutableMap<String, String?> = mutableMapOf()

    fun addChangedRecord(initialName: String, initialValue: String?) {
        if (!initialStateNameToValue.contains(initialName)) {
            initialStateNameToValue[initialName] = initialValue
        }
    }

    fun getChangedRecordsBeforeTransaction(): Map<String, String?> {
        return initialStateNameToValue
    }
}
