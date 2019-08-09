package dashfwd.arrowOption

import arrow.core.Option
import arrow.core.none
import arrow.core.toOption

class Musician(val name: String)

/**
 * Classes often have optional or nullable data members, and
 * one common pattern is needing to pull out something contained
 * in that data member, or a default value if it doesn't exist.
 *
 * This class shows a demo of how to do that using idiomatic Kotlin
 * (using nulls) and how the same thing would be achieved using
 * Arrow's "Option" class.
 */
fun traverseUsingNulls() {
    class Band(val drummer: Musician?)
    class Manager(val band: Band?)
    class RecordLabel(val manager: Manager?)
    fun getDrummerName(recordLabel: RecordLabel):String {
        return recordLabel.manager?.band?.drummer?.name ?: "None"
    }

    val label1 = RecordLabel(Manager(Band(Musician("Mick Shrimpton"))))
    println("[traverse using null] Label 1 = ${getDrummerName(label1)}")

    val label2 = RecordLabel(Manager(null))
    println("[traverse using null] Label 2 = ${getDrummerName(label2)}")
}

fun traverseUsingOption() {
    class Band(val drummer: Option<Musician>)
    class Manager(val band: Option<Band>)
    class RecordLabel(val manager: Option<Manager>)
    fun getDrummerName(recordLabel: RecordLabel):String {
        return recordLabel.manager
            .flatMap { x -> x.band }
            .flatMap { x -> x.drummer }
            .fold({ "None" },{ x -> x.name }
        )
    }
    val label1 = RecordLabel(Manager(Band(Musician("Mick Shrimpton").toOption()).toOption()).toOption())
    println("[traverse using Option] Drummer 1 = ${getDrummerName(label1)}")

    val label2 = RecordLabel(Manager(none()).toOption())
    println("[traverse using Option] Drummer 2 = ${getDrummerName(label2)}")
}

fun main() {
    traverseUsingNulls()
    traverseUsingOption()
}