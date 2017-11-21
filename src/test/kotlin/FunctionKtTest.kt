import azure.tika.GreetingResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.junit.Test
import kotlin.test.assertTrue


class FunctionKtTest {
    val jsonAdapter: JsonAdapter<GreetingResponse> = Moshi.Builder().build()
            .adapter(GreetingResponse::class.java)

    @Test
    fun testGreetingWithoutName() {
        assertTrue {
            jsonAdapter.fromJson(azure.tika.greeting()) == GreetingResponse(response = "Hello, World")
        }
    }

    @Test
    fun testGreetingWithName() {
        assertTrue {
            jsonAdapter.fromJson(azure.tika.greeting(name = "Azure")) == GreetingResponse(response = "Hello, Azure")
        }
    }
}
