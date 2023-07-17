package tinqin.zoostorage.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "tinqin.zoostorage")
@EntityScan(basePackages = "tinqin.zoostorage.persistance.data")
@EnableJpaRepositories(basePackages = "tinqin.zoostorage.persistance.repository")
public class ZooStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooStorageApplication.class, args);
    }
}
