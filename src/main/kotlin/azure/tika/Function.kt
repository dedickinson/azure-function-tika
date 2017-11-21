package azure.tika

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

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

fun main(args: Array<String>) = println(greeting())
