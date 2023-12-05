package db

class DecoratingDataBase(
    private val db: DataBase
): DataBase {

    override fun set(name: String, value: String) {
        println(">>> SET $name $value")
        db.set(name, value)
    }

    override fun get(name: String): String {
        println(">>> GET $name")
        return db.get(name)
    }

    override fun delete(name: String) {
        println(">>> DELETE $name")
        db.delete(name)
    }

    override fun count(value: String): Int {
        println(">>> COUNT $value")
        return db.count(value)
    }

    override fun begin() {
        println(">>> BEGIN")
        db.begin()
    }

    override fun rollback() {
        println(">>> ROLLBACK")
        db.rollback()
    }

    override fun commit() {
        println(">>> COMMIT")
        db.commit()
    }
}