package personal.chris.aish

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.runBlocking
import java.io.File

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {

    val apiKeyPath: String = System.getenv("HOME") + "/.aish/apikey"
    logger.info { "Reading key from path: $apiKeyPath" }
    val apiKey: String = File(apiKeyPath).readText().trim()
    logger.debug { "API key: $apiKey" }

    if (args.isEmpty()) {
        println("Usage: aish <query>")
        return
    } else if (args[0] == "nocall") {
        // Don't actually make an API call, just give back our prompt for inspection by the user
        val query = args.drop(1).joinToString(" ")
        println("First arg was nocall, skipping API call. The prompt is:")
        println(Prompter.prompt(query))
        return
    } else {
        // Generate the prompt and send it
        val query = args.joinToString(" ") // The input query
        val prompt = Prompter.prompt(query)
        var response: String? = null
        runBlocking {
            response = OpenAIClient.callOpenAI(prompt, apiKey)
            println(response)
        }

        // If the response is a shell script, offer to run it
        if (response != null && response!!.startsWith("```sh") && response!!.endsWith("```")) {
            println("Would you like to run this script? (y/N)")
            val input = readLine()
            if (input == "y") {
                val script = response!!.removePrefix("```sh").removeSuffix("```").trim()
                logger.info { "Running script: $script" }
                ShellTools.appendToHistory(script)
                val process = ProcessBuilder("sh", "-c", script).inheritIO().start()
                process.waitFor()
            }
        }
    }
}
