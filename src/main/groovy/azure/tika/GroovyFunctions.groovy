
package azure.tika

import com.microsoft.azure.serverless.functions.ExecutionContext
import groovy.json.JsonOutput
import org.apache.tika.io.TikaInputStream
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.parser.ParseContext
import org.apache.tika.sax.BodyContentHandler

class GroovyGreetingResponse {
    final String response
}

class GroovyTikaResponse {
    Metadata metadata
    String content
}

class GroovyFunctions {

    static GroovyGreetingResponse groovyGreeting(String name = 'World', ExecutionContext context = null) {
        context.logger.info("groovyGreeting Called")
        return new GroovyGreetingResponse(response: "Hello, ${name?: 'World'}")
    }

    static GroovyTikaResponse groovyTika(byte[] content, ExecutionContext context = null) {
        context.logger.info("groovyTika Called")

        def parser = new AutoDetectParser()
        def handler = new BodyContentHandler()
        def metadata = new Metadata()
        def parseContext = new ParseContext()

        InputStream inputStream = new ByteArrayInputStream(content)

        def stream = TikaInputStream.get(inputStream)
        parser.parse(stream, handler, metadata, parseContext)

        return new GroovyTikaResponse(metadata: metadata,
                content: handler.toString())
    }
}
