package tinqin.zoostorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tinqin.zoostorage.data.Storage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StorageRepository  extends JpaRepository<Storage, UUID> {
    Storage getStorageByItemIdAndCity(UUID itemId, String city);
    boolean existsByItemId(UUID itemId);
    List<Storage> findAllByItemId(UUID itemId);
    Storage getStorageById(UUID storageId);
    boolean existsByCity(String city);
}
