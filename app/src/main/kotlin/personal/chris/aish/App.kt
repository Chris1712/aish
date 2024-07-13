package personal.chris.aish

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
import java.io.File

private val logger = KotlinLogging.logger {}

class App {
    val apiKeyPath: String = System.getenv("HOME") + "/.aish/apikey"
    val apiKey: String = File(apiKeyPath).readText().trim()
}

fun main() {
    logger.info { "Reading key from path: ${App().apiKeyPath}" }
    logger.info { "API key: ${App().apiKey}" }
    val client = OpenAIClient(App().apiKey)

    runBlocking {
        val response = client.callOpenAI("Tell me a joke.")
        println(response)
    }

}
