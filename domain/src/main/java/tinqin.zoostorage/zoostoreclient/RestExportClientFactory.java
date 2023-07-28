package tinqin.zoostorage.zoostoreclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tinqin.zoostore.ZooStoreRestClient;

@Configuration
@RequiredArgsConstructor
public class RestExportClientFactory {
    @Bean
    public ZooStoreRestClient getMyRestExportClient() {
        final ObjectMapper objectMapper = new ObjectMapper();

        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStoreRestClient.class, "http://localhost:8080");
    }
}