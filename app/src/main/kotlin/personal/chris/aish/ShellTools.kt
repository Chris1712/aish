package personal.chris.aish

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import java.time.Instant

private val logger = KotlinLogging.logger {}

object ShellTools {

    fun shell(): String {
        return System.getenv("SHELL")?.substringAfterLast("/") ?: "Unknown"
    }

    fun appendToHistory(script: String) {
        val shell = shell()
        when (shell) {
            "zsh" -> appendToZshHistory(script)
            "bash" -> appendToBashHistory(script)
            else -> logger.info { "Shell $shell not supported" }
        }
    }

    private fun appendToZshHistory(script: String) {
        val homeDir = System.getenv("HOME")
        val zshHistoryPath = "$homeDir/.zsh_history"
        val currentTimeInSeconds = Instant.now().epochSecond
        val historyEntry = ": $currentTimeInSeconds:0;$script\n"
        File(zshHistoryPath).appendText(historyEntry)
    }

    private fun appendToBashHistory(script: String) {
        val homeDir = System.getenv("HOME")
        val bashHistoryPath = "$homeDir/.bash_history"
        File(bashHistoryPath).appendText("$script\n")
    }

}
