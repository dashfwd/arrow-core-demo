package dashfwd.arrowOption

import arrow.core.*

fun main() {
    // Creating Options (the typical way)
    val colorOption1:Option<String> = Some("purple")
    val colorOption2:Option<String> = none();

    // Check if the option is defined (does it exist?)
    println ("colorOption1.isDefined() = ${colorOption1.isDefined()}") // true
    println ("colorOption2.isDefined() = ${colorOption2.isDefined()}") // false

    // Is it empty?
    println ("colorOption1.isEmpty() = ${colorOption1.isEmpty()}") // false
    println ("colorOption2.isEmpty() = ${colorOption2.isEmpty()}") // true

    // Same idea, but less typing
    println ("colorOption1 is Some = ${colorOption1 is Some}") // true
    println ("colorOption1 is None = ${colorOption1 is None}") // false

    // Using "getOrElse { }" to convert "None" to a value
    println ("colorOption1 = ${colorOption1.getOrElse { "(transparent)" }}") // purple
    println ("colorOption2 = ${colorOption2.getOrElse { "(transparent)" }}") // (transparent)

    // getting the value -- a poor implementation
    val displayValue = if (colorOption1.isDefined()) {
        (colorOption1 as Some<String>).t
    }
    else {
        "transparent"
    }
    println("colorOption1 value manually=$displayValue")

    // Using "when" to convert "None" to a value, with smart casts
    val asString = when (colorOption2) {
        is None -> "(transparent)"
        is Some -> colorOption2.t
    }
    println ("colorOption2 (via when) = $asString") // (transparent)

    // Other ways to create options
    val kotlinNullableString:String? = "red"
    val kotlinNullableString2:String? = null

    val options = listOf (
        Some("blue"),
        None,
        Option.fromNullable(kotlinNullableString),
        Option.fromNullable(kotlinNullableString2),
        kotlinNullableString.toOption(),
        "orange".some(),
        none<String>()
    )

    options.forEach { maybeName ->
        println("Color is " + maybeName.getOrElse { "(transparent)" })
    }


    val some10 = Some(10)
    val some11 = some10.map { it + 1}
    println ("Some 11 = " + some11.getOrElse { "n/a" })
}