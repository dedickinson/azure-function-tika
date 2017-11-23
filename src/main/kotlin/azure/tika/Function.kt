package azure.tika

import com.microsoft.azure.serverless.functions.ExecutionContext
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import org.apache.tika.io.TikaInputStream
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.parser.ParseContext
import org.apache.tika.sax.BodyContentHandler

data class KotlinGreetingResponse(val response: String = "")

class KotlinGreetingResponse2(val response: String = "")

val jsonAdapter: JsonAdapter<KotlinGreetingResponse> = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
        .adapter(KotlinGreetingResponse::class.java)

fun kotlinGreetingJson(name: String? = "World", context: ExecutionContext): String {
    context.logger.info("kotlinGreetingJson Called")
    return jsonAdapter.toJson(
            KotlinGreetingResponse(response = "Hello, ${name ?: "World"}")
    )
}

fun kotlinGreetingDataClass(name: String? = "World", context: ExecutionContext): KotlinGreetingResponse {
    context.logger.info("kotlinGreetingDataClass Called")
    return KotlinGreetingResponse(response = "Hello, ${name ?: "World"}")
}

fun kotlinGreetingClass(name: String? = "World", context: ExecutionContext): KotlinGreetingResponse2 {
    context.logger.info("kotlinGreetingClass Called")
    return KotlinGreetingResponse2("Hello, ${name ?: "World"}")
}

fun kotlinTika(content: ByteArray, context: ExecutionContext): String {
    context.logger.info("kotlinTika Called")

    val parser = AutoDetectParser()
    val handler = BodyContentHandler()
    val metadata = Metadata()
    val parseContext = ParseContext()

    val stream = TikaInputStream.get(content.inputStream())
    parser.parse(stream, handler, metadata, parseContext)

    return metadata.get("Content-Type")
}

