package nullability


fun main() {
//    var favoriteActor: String = "Sandra Oh"
//    favoriteActor = null -> won't work because Null can not be a value of a non-null type String

    // to declare nullable variables in Kotlin, you need to add a ? operator to the end of the type.
    var favoriteActor: String? = "Sandra Oh"
    println(favoriteActor)
    favoriteActor = null
    println(favoriteActor)

    var number: Int? = 10
    println(number)
    number = null
    println(number)

    // handle nullable variables
    var favoriteActorNullable: String? = "Sandra Oh"
//    println(favoriteActorNullable.length) won't work since only safe (?.) or non-null asserted (!!.) calls
//    are allowed on a nullable receiver of type String?
    println(favoriteActorNullable?.length)
    println(favoriteActorNullable!!.length)
    var favoriteActorNull: String? = null
//    println(favoriteActorNull!!.length) -> NullPointerException


    // Elvis operator
//    The ?: Elvis operator is an operator that you can use together with the ?. safe-call operator.
//    With the ?: Elvis operator, you can add a default value when the ?. safe-call operator returns null.
//    It's similar to an if/else expression, but in a more idiomatic way.
//    If the variable isn't null, the expression before the ?: Elvis operator executes. If the variable is null, the expression after the ?: Elvis operator executes.
    val faveActor: String? = "Sandra Oh"
    val lengthOfName = faveActor?.length ?: 0
    println("The number of characters in your favorite actor's name is $lengthOfName.")

}