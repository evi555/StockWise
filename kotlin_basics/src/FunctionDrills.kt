import java.io.StringReader

/*fun main() {
    println("What is your name?")

    val name = readLine()
    println("What is your Age?")
    val age = readLine()


    println(
            if (!age.isNullOrBlank() && age.toInt()> 18)
                "$name is old enough to drive"
            else
                "$name is too young to drive, Sorry"
    )
}


 */

/*
fun main() {

    listOf("moshe","dave","yael")

    println(concat(listOf("moshe","dave","yael")))
    println(concat(listOf("moshe","dave","yael"), separator = "&"))
}



fun concat(strings: List<String>, separator : String = ",") = strings.joinToString(separator)



 */


fun main() {
    printMessage("5765")
    printMessageWithPrefix("world","hello")
    printMessageWithPrefix("world")
    println(sum(9,7))
    println(mul(6,8))
    printAll("elnini","fernando","fridayboxing","balbal")

    val m = mapOf("eviatar" onto "noy")
    println(m)
}

infix fun String.onto(other:String) = Pair(this,other)
fun printAll(vararg messages:String){
    for(n in messages)
        println(n)
}

fun sum(a:Int, b:Int)= a+b

fun mul(a:Int,b:Int) = a*b

fun printMessageWithPrefix(message: String , prefix:String ="Info"){
    println("$prefix $message")
}

fun printMessage(string:String)  {
    println(string)
}