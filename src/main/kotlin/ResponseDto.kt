import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
class ResponseDto(val userId: Int, val id: Int, val title: String, val body: String)

//@Serializable
//data class ResponseDtoList(val itemList: List<ResponseDto>)

//@Serializable
//class ResponseDtoList(
//  val items: List<ResponseDto>
//) {
//
//  @Serializer(ResponseDtoList::class)
//  companion object : KSerializer<ResponseDtoList> {
//
//    override val descriptor = StringDescriptor.withName("ResponseDtoList")
//
//    override fun serialize(output: Encoder, obj: ResponseDtoList) {
//      ResponseDto.serializer().list.serialize(output, obj.items)
//    }
//
//    override fun deserialize(input: Decoder): ResponseDtoList {
//      return ResponseDtoList(ResponseDto.serializer().list.deserialize(input))
//    }
//  }
//}

