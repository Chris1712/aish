package personal.chris.aish

import java.io.File

class App {
    val apiKeyPath: String = System.getenv("HOME") + "/.aish/apikey"
    val apiKeyContent: String = File(apiKeyPath).readText()
}

fun main() {
    println(App().apiKeyPath)
    println(App().apiKeyContent)

    // Load a file from ~/.secret
    val secret = System.getenv("HOME") + "/.secret"


}
