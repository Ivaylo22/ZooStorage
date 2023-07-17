package tinqin.zoostorage.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tinqin.zoostorage.persistance.data.Storage;

import java.util.UUID;

@Repository
public interface StorageRepository  extends JpaRepository<Storage, UUID> {
    Storage getStorageByItemId(UUID itemId);
    Storage getStorageById(UUID storageId);
}
