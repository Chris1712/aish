package personal.chris.aish

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
import java.io.File

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {

    val apiKeyPath: String = System.getenv("HOME") + "/.aish/apikey"
    logger.info { "Reading key from path: $apiKeyPath" }
    val apiKey: String = File(apiKeyPath).readText().trim()
    logger.info { "API key: $apiKey" }

    if (args.size == 0) {
        println("Usage: aish <query>")
        return
    } else if (args[0] == "nocall") {
        val query = args.drop(1).joinToString(" ")
        println("First arg was nocall, skipping API call. The prompt is:")
        println(Prompter.prompt(query))
        return
    } else {
        val query = args.joinToString(" ") // The input query
        val prompt = Prompter.prompt(query)
        runBlocking {
            val response = OpenAIClient.callOpenAI(prompt, apiKey)
            println(response)
        }
    }
}
