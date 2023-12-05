package db

class InMemoryDataBase : DataBase {

    private val nameToValue: MutableMap<String, String> = mutableMapOf()
    private val valueToNames: MutableMap<String, MutableSet<String>> = mutableMapOf()

    private val openTransactions = ArrayDeque<Transaction>(listOf())

    override fun set(name: String, value: String) {
        openTransactions.lastOrNull()?.addChangedRecord(name, nameToValue[name])
        delete(name)
        nameToValue[name] = value
        if (!valueToNames.contains(value)) {
            valueToNames[value] = mutableSetOf()
        }
        valueToNames[value]?.add(name)
    }

    override fun get(name: String): String {
        return nameToValue[name] ?: "NULL"
    }

    override fun delete(name: String) {
        openTransactions.lastOrNull()?.addChangedRecord(name, nameToValue[name])
        val value = nameToValue.remove(name)
        valueToNames[value]?.remove(name)
        if (valueToNames[value]?.isEmpty() == true) {
            valueToNames.remove(value)
        }
    }

    override fun count(value: String): Int {
        return valueToNames[value]?.size ?: 0
    }

    override fun begin() {
        openTransactions.addLast(Transaction())
    }

    override fun rollback() {
        if (openTransactions.isEmpty()) {
            println("NO TRANSACTION")
        } else {
            val transaction = openTransactions.removeLast()
            val nameToValueInitial = transaction.getChangedRecordsBeforeTransaction()
            nameToValueInitial.forEach { (name, value) ->
                if (value == null) {
                    this.delete(name)
                } else {
                    this.set(name, value)
                }
            }
        }
    }

    override fun commit() {
        if (openTransactions.isEmpty()) {
            println("NO TRANSACTION")
        } else {
            openTransactions.clear()
        }
    }
}
