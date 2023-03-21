package conditionals

fun main() {
    // using if conditionals
    val trafficLightIf = "Black"

    if (trafficLightIf == "Red") {
        println("Stop")
    } else if (trafficLightIf == "Yellow") {
        println("Slow")
    } else if (trafficLightIf == "Green") {
        println("Go")
    } else {
        println("Invalid traffic-light-if color")
    }

    // using when
    val trafficLightWhen = "Amber"

    when (trafficLightWhen) {
        "Red" -> println("Stop")
        "Yellow", "Amber" -> println("Slow")
        "Green" -> println("Go")
        else -> println("Invalid traffic-light-when color")
    }

    val message = when(trafficLightWhen) {
        "Red" -> "Message: Stop"
        "Yellow", "Amber" -> "Message: Slow"
        "Green" -> "Message: Go"
        else -> "Message: Invalid traffic-light color"
    }
    println(message)
}