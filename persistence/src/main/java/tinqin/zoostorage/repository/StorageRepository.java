package tinqin.zoostorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tinqin.zoostorage.data.Storage;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorageRepository  extends JpaRepository<Storage, UUID> {
    Storage getStorageByItemId(UUID itemId);
    boolean existsByItemId(UUID itemId);
    Storage findByItemId(UUID itemId);
    Storage getStorageById(UUID storageId);
}
