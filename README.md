## Run

### 1. Install OpenTelemetry

 - Download OpenTelemetry Collector from https://opentelemetry.io/docs/collector/getting-started/#windows-packaging

 - Start otel-collector

   ```
   otelcol.exe --config=./otel-collector-config.yaml
   ```

   ```yaml
   # otel-collector-config.yaml
   receivers:
     # Make sure to add the otlp receiver.
     # This will open up the receiver on port 4317
     otlp:
       protocols:
         grpc:
           endpoint: "0.0.0.0:4317"
   processors:
   extensions:
     health_check: {}
   exporters:
     jaeger:
       endpoint: "http://localhost:14250/"
     zipkin:
       endpoint: "http://localhost:9411/api/v2/spans"
     logging:
       loglevel: debug
   service:
     extensions: [health_check]
     pipelines:
       traces:
         receivers: [otlp]
         processors: []
         exporters: [jaeger, zipkin, logging]
   ```



### 2. Install Jaeger

- Download Jaeger from https://www.jaegertracing.io/docs/1.38/getting-started/
- Use CMD to run `jaeger-all-in-one.exe`
- You can then navigate to `http://localhost:16686` to access the Jaeger UI.



### 3. Install Zipkin

If you have Java 8 or higher installed, the quickest way to get started is to fetch the [latest release](https://search.maven.org/remote_content?g=io.zipkin&a=zipkin-server&v=LATEST&c=exec) as a self-contained executable jar:

```sh
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```



### 4. Start Spring Application

1. Start `api-service` with VM options:

   ```
   -javaagent:C:/Dev/repository/tzhong/tzhong/spring-cloud-sleuth-poc/otel-collector/opentelemetry-javaagent.jar
   -Dotel.service.name=api-service
   -Dotel.traces.exporter=jaeger
   -Dotel.exporter.jaeger.endpoint=http://localhost:14250
   -Dotel.javaagent.debug=true
   ```

2. Start `customer-service` with VM options:

   ```
   -javaagent:C:/Dev/repository/tzhong/tzhong/spring-cloud-sleuth-poc/otel-collector/opentelemetry-javaagent.jar
   -Dotel.service.name=customer-service -Dotel.traces.exporter=jaeger 
   -Dotel.exporter.jaeger.endpoint=http://localhost:14250 
   -Dotel.javaagent.debug=true
   ```

   

### 5. Test

```sh
curl --location --request GET 'http://localhost:9090/customers/1'
```



## Reference

1. https://spring.io/projects/spring-cloud-sleuth

   

2. Not seeing traceids in the http response headers.
   - From the security perspective, it is not too safe. spring-cloud-sleuth remove it officially.
   
   - https://github.com/spring-cloud/spring-cloud-sleuth/issues/424
   
     
   
3. spring-cloud-sleuth及自定义日志链路traceId、spanId跟踪
   - https://blog.csdn.net/xibei19921101/article/details/119736123

     
   
4. https://zipkin.io/pages/quickstart

   ```sh
   # If you have Java 8 or higher installed, the quickest way to get started is to fetch the latest release as a self-contained executable jar:
   
   curl -sSL https://zipkin.io/quickstart.sh | bash -s
   java -jar zipkin.jar
   ```

5. OpenTelemetry

   - Download OpenTelemetry Collector https://opentelemetry.io/docs/collector/getting-started/#windows-packaging

   - otel-collector

      ```
      # How to start?
      otelcol.exe --config=./otel-collector-config.yaml
      ```

   - otel-collector-config.yaml -> https://opentelemetry.io/docs/collector/configuration/

     

6. Jaeger

   - https://www.jaegertracing.io/docs/1.38/getting-started/

   - Start Jaeger on Windows. Then navigate to `http://localhost:8080`

     ```
     example-hotrod all
     ```

     

7. Comparing Jaeger and Zipkin



