package db

interface DataBase {

    fun set(name: String, value: String)

    fun get(name: String): String

    fun delete(name: String)

    fun count(value: String): Int

    fun begin()

    fun rollback()

    fun commit()
}
