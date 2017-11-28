package azure.tika;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class JavaFunctions {

    static public JavaGreetingResponse javaGreeting(Optional<String> name, ExecutionContext context) {
        context.getLogger().info("javaGreeting Called");

        String output = String.format("Hello, %s", name.orElse("World"));
        return new JavaGreetingResponse(output);
    }

    static public JavaTikaResponse javaTika(byte[] content, ExecutionContext context)
            throws TikaException, SAXException, IOException {
        context.getLogger().info("javaTika Called");

        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();

        InputStream inputStream = new ByteArrayInputStream(content);

        InputStream stream = TikaInputStream.get(inputStream);
        parser.parse(stream, handler, metadata, parseContext);

        return new JavaTikaResponse(metadata, handler.toString());
    }
}
