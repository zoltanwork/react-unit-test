import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

//class RestClient(engineFactory: HttpClientEngineFactory<*>) {
class RestClient(engine: HttpClientEngine) {
    var urlAddress = "https://jsonplaceholder.typicode.com/posts"
    private val client = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getData(): List<ResponseDto> {
        println("getData ------------------------------------------------------")
        val data: String = client.get(urlAddress)
        val list = Json.decodeFromString(ListSerializer(ResponseDto.serializer()), data)
        println("getData result size: ${list.size}")
//        delay(100)
        return list
    }
}

