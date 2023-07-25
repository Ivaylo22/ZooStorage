package tinqin.zoostorage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "tinqin.zoostorage.controllers")
@ComponentScan(basePackages = "tinqin.zoostorage.model")
@EntityScan(basePackages = "tinqin.zoostorage.data")
@EnableJpaRepositories(basePackages = "tinqin.zoostorage.repository")
public class ZooStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZooStorageApplication.class, args);
    }
}
