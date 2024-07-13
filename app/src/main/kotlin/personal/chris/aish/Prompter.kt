package personal.chris.aish

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {}

object Prompter {
    /**
     * Generates a prompt for the user based on the input query, and other possibly useful context
     */
    fun prompt(query: String): String {
        return """
            You are an AI assistant tool, being called from a CLI. Your task is to provide a short sh script to help
            the user with their query. The script should be wrapped inside ```sh blocks ```
            DO NOT RETURN ANY TEXT OUTSIDE OF THE CODE BLOCKS
            If the users question is more open-ended, provide a short text answer instead, with no code.
             
            Example usage:
            User: "How do I install curl"?
            You: ```sh
            sudo apt install curl
            ```
             
            User: "What is the capital of France"?
            You: Paris
            
            The current shell is: ${shell()}
            The current working directory is ${System.getProperty("user.dir")}
            The files in the current directory are: ${ls()}
             
            $query
        """.trimIndent()
    }

    /**
     * List the files in the current directory
     */
    private fun ls(): List<String> {
        val workingDirectory = System.getProperty("user.dir")
        logger.debug { "Working directory: $workingDirectory" }
        return File(workingDirectory).listFiles()?.map { it.name } ?: emptyList()
    }

    private fun shell(): String {
        return System.getenv("SHELL") ?: "Unknown"
    }
}
