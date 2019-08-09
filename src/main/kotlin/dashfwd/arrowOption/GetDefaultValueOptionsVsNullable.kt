package dashfwd.arrowOption

import arrow.core.Option
import arrow.core.none
import arrow.core.toOption

class Person(val name: String)

/**
 * Often a class has an optional (or nullable) data member, and
 * you need to either use that value or a default value.
 *
 * This class shows a demo of how to do that using idiomatic Kotlin
 * (using nulls) and how the same thing would be achieved using
 * Arrow's "Option" class.
 */
fun usingNulls() {
    class Band(val drummer: Person?)
    fun getDrummerName(band:Band):String {
        // Here "band.drummer?" will return either Person or null
        // The elvis operator "?:" will convert the null into "None"
        return band.drummer?.name ?: "None"
    }

    val band1 = Band(Person("Mick Shrimpton"))
    println("[using null] Drummer 1 = ${getDrummerName(band1)}")

    val band2 = Band(null)
    println("[using null] Drummer 2 = ${getDrummerName(band2)}")
}

fun usingOption() {
    class Band(val drummer: Option<Person>)
    fun getDrummerName(band:Band):String {
        return band.drummer.fold(
            { "None" },              // <- default value if drummer is "None"
            { x -> x.name }  // <- otherwise, grab "name" from the "Person" instance
        )
    }
    val band1 = Band(Person("Mick Shrimpton").toOption())
    println("[using Option] Drummer 1 = ${getDrummerName(band1)}")

    val band2 = Band(none())
    println("[using Option] Drummer 2 = ${getDrummerName(band2)}")
}

fun main() {
    usingNulls()
    usingOption()
}