# Apache Tika Azure Function

For this project I'm going to setup a basic Azure Function that accepts a POST request (with a file in the body)
and use [Apache Tika](https://tika.apache.org/) to return the file metadata and full text.

I've been wanting to try out 
[Java support for Azure Functions](https://azure.microsoft.com/en-au/blog/announcing-the-preview-of-java-support-for-azure-functions/) 
since it was announced. There's no hating on C# here - I've been working to develop my
skills in the .Net space since core was released. However, Java has a huge body
of quality open source libraries and it's useful to take a polygot approach to serverless environments.

I don't make life easy for myself though. First up, I want to use [Gradle](https://gradle.org/)
and not Maven - I've just found it so much easier. I also used Kotlin as it's something I'm trying
to also pick up (too many languages, too little time). Lastly, I'm using OS X and IntelliJ IDEA and
not Visual Studio. YOLO.

### Other references

- [Azure Functions Java developer guide](https://docs.microsoft.com/en-us/azure/azure-functions/functions-reference-java)
- [Java runtime for Microsoft Azure Functions](https://github.com/Azure/azure-functions-java-worker)
- [Code and test Azure Functions locally](https://docs.microsoft.com/en-us/azure/azure-functions/functions-run-local)

## Preparing OS X 

### Install Java 8

If you don't have Java 8 installed I'd recommend you take a look 
at [SDKMAN!](http://sdkman.io/) - it's easy to install and to manage 
a variety of SDKs.

Once you've installed SDKMAN!, it's easy to get the JDK - here's 
what I did:

````
sdk list java
sdk install 8u144-zulu
````

Note that you don't need to install Gradle or Kotlin - the Gradle build
file will do this.

### Install the Azure Functions Core tools

As per [the doco](https://docs.microsoft.com/en-us/azure/azure-functions/functions-run-local):

1. Install [.NET Core 2.0](https://www.microsoft.com/net/learn/get-started/macos)
2. Install `Node`, `NPM` and `azure-functions-core-tools@core`:
    ````
    sudo port install nodejs8 npm5
    npm install -g azure-functions-core-tools@core
    ````

## Deploy 

The [Gradle Wrapper](https://docs.gradle.org/4.3.1/userguide/gradle_wrapper.html)
makes light work of building the functions:

````
./gradlew packageAzureFunction
````

To run up the functional locally:

````
./start-func.sh
```` 

## Test the functions

For the tika functions, you need to POST a file for investigating and set the request's `content-type`
to `application/octet-stream` - as per the `curl` commands below:

Java:

    curl -X POST http://localhost:7071/api/javatika \
      -H 'accept:n' \
      -H 'cache-control: no-cache'   \
      -H 'content-type: application/octet-stream' \
      --data-binary @src/test/resources/TikaTestDocument.pdf


Groovy:

    curl -X POST http://localhost:7071/api/groovytika \
      -H 'accept:n' \
      -H 'cache-control: no-cache'   \
      -H 'content-type: application/octet-stream' \
      --data-binary @src/test/resources/TikaTestDocument.pdf

Kotlin

    curl -X POST http://localhost:7071/api/kotlintika \
          -H 'accept:n' \
          -H 'cache-control: no-cache'   \
          -H 'content-type: application/octet-stream' \
          --data-binary @src/test/resources/TikaTestDocument.pdf
