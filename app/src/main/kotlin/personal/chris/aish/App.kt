package personal.chris.aish

import kotlinx.coroutines.runBlocking
import java.io.File

class App {
    val apiKeyPath: String = System.getenv("HOME") + "/.aish/apikey"
    val apiKey: String = File(apiKeyPath).readText().trim()
}

fun main() {
    println(App().apiKeyPath)
    println(App().apiKey)

    val client = OpenAIClient(App().apiKey)

    runBlocking {
        val response = client.callOpenAI("Tell me a joke.")
        println(response)
    }

}
