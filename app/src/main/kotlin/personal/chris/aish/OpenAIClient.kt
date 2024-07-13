package personal.chris.aish

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class OpenAIRequest(
    val model: String,
    val messages: List<Message>,
    val max_tokens: Int,
    val temperature: Double
)

@Serializable
data class Message(
    val role: String,
    val content: String
)

@Serializable
data class OpenAIResponse(
    val id: String,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage
)

@Serializable
data class Choice(
    val message: Message,
    val finish_reason: String
)

@Serializable
data class Usage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int
)

private val logger = KotlinLogging.logger {}

object OpenAIClient {

    suspend fun callOpenAI(prompt: String, apiKey: String): String {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        val request = OpenAIRequest(
            model = "gpt-4o",
            messages = listOf(Message("user", prompt)),
            max_tokens = 1000,
            temperature = 0.0
        )

        logger.info { "Calling OpenAI with request: $request" }
        val response: HttpResponse = client.post("https://api.openai.com/v1/chat/completions") {
            header("Authorization", "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(request)
        }

        val bodyStr = response.bodyAsText()
        logger.debug { "Response status: ${response.status}; body: $bodyStr" }

        if (response.status != HttpStatusCode.OK) {
            return "Error calling API; status: ${response.status}; body: $bodyStr"
        }

        val openAIResponse: OpenAIResponse = response.body()
        val usage : Usage = openAIResponse.usage

        logger.info { "Tokens used: (intput/output/total):  " +
                "${usage.prompt_tokens} / ${usage.completion_tokens} / ${usage.total_tokens}" }
        return openAIResponse.choices.first().message.content
    }

}
