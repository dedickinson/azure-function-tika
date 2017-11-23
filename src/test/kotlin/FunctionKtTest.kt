import azure.tika.KotlinGreetingResponse
import com.microsoft.azure.serverless.functions.ExecutionContext
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.junit.Test
import kotlin.test.assertTrue


class FunctionKtTest {
    val jsonAdapter: JsonAdapter<KotlinGreetingResponse> = Moshi.Builder().build()
            .adapter(KotlinGreetingResponse::class.java)

    /*
    @Test
    fun testGreetingWithoutName() {
        assertTrue {
            jsonAdapter.fromJson(azure.tika.greeting()) == KotlinGreetingResponse(response = "Hello, World")
        }
    }

    @Test
    fun testGreetingWithName() {
        assertTrue {
            jsonAdapter.fromJson(azure.tika.greeting(name = "Azure")) == KotlinGreetingResponse(response = "Hello, Azure")
        }
    }
    */
}
