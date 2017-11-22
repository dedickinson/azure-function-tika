package azure.tika

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import org.apache.tika.io.TikaInputStream
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import java.io.File

data class GreetingResponse(val response: String = "")

val jsonAdapter: JsonAdapter<GreetingResponse> = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
        .adapter(GreetingResponse::class.java)

fun greeting(name: String? = "World"): String {

    // We still need to check `name` for NULL as the Azure Function
    // passes in Null, pushing past the default. `name` is marked as
    // nullable as we want to handle an empty request body

    return jsonAdapter.toJson(
            GreetingResponse(response = "Hello, ${name?:"World"}")
    )
}

fun tika(content: ByteArray): String {
    val parser = AutoDetectParser()
    val handler = BodyContentHandler()
    val metadata = Metadata()
    val parseContext = ParseContext()

    val stream = TikaInputStream.get(content.inputStream())
    parser.parse(stream, handler, metadata, parseContext)

    return metadata.get("Content-Type")
}

fun main(args: Array<String>) {
    if (args.size != 1) throw IllegalArgumentException("Require 1 input parameter")

    println(tika(File(args[0]).readBytes()))

}
