import java.io.IOException

fun main() {
    println("Hello Kotlin!!")

    val result = try {
        write(byteArrayOf())

    }catch (ex : IOException){
        println("error")
        false
    }finally {
        println("in finally")
    }
    println(result)

}


fun write(bytes:ByteArray?): Boolean {
    if(bytes==null || bytes.isEmpty()) throw IOException()
    return true
}