package tinqin.zoostorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tinqin.zoostorage.data.Sale;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
}
