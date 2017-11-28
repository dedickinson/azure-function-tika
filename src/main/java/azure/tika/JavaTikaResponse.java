package azure.tika;

import org.apache.tika.metadata.Metadata;

public class JavaTikaResponse {
    private final Metadata metadata;
    private final String content;


    public JavaTikaResponse(Metadata metadata, String content) {
        this.metadata = metadata;
        this.content = content;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public String getContent() {
        return content;
    }
}
