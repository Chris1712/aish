package personal.chris.aish

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

object Prompter {
    fun prompt(query: String): String {
        ls()


        return """
            
            $query
        """.trimIndent()

    }

    /**
     * List the files in the current directory
     */
    private fun ls(): List<String> {
        val workingDirectory = System.getProperty("user.dir")
        logger.info { "Working directory: $workingDirectory" }
        return listOf()
    }
}
