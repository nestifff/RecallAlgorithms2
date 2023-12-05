import db.DecoratingDataBase
import db.InMemoryDataBase

fun main() {
    val db = DecoratingDataBase(InMemoryDataBase())
    // 1
    /*db.begin()
    db.set("a", "10")
    println(db.get("a"))
    db.begin()
    db.set("a", "20")
    println(db.get("a"))
    db.rollback()
    println(db.get("a"))
    db.rollback()
    db.get("a")*/

    // 2
    /*db.begin()
    db.set("a", "30")
    db.set("a", "40")
    db.get("a")
    db.rollback()
    db.get("a")
    db.count("30")*/

    //3
    db.set("a", "50")
    db.begin()
    println(db.get("a"))
    db.set("a", "60")
    db.begin()
    db.delete("a")
    println(db.get("a"))
    db.rollback()
    println(db.get("a"))
    db.commit()
    println(db.get("a"))

    //4
//    db.set("a", "10")
//    db.begin()
//    db.count("10")
//    db.begin()
//    db.delete("a")
//    db.count("10")
//    db.rollback()
//    db.count("10")
}
