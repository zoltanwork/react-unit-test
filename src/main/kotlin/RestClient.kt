import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class RestClient(engine: HttpClientEngine) {
    var urlAddress = "https://jsonplaceholder.typicode.com/posts"
    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getData(): List<ResponseDto> {
        println("getData ------------------------------------------------------")
        try {
            val list: List<ResponseDto> = client.get(urlAddress)
            println("getData result size: ${list.size}")
            return list
        }
        catch (e: Exception) {
            println("\nexception: $e")
            throw e
        }
    }
}

