import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

fun readIn(name: String) = Path("src/$name.txt").readText()

fun List<String>.splitIfIs(string: String): List<List<String>> {
    val list = ArrayList<ArrayList<String>>()
    var buf = ArrayList<String>()
    this.forEach {
        if (it == string) {
            list.add(buf)
            buf = ArrayList()
        } else {
            buf.add(it)
        }
    }
    list.add(buf)
    return list
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
