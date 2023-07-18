package tinqin.zoostorage.core;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.api.model.delete_storage.DeleteRequest;
import tinqin.zoostorage.api.model.delete_storage.DeleteResponse;
import tinqin.zoostorage.api.model.delete_storage.DeleteStorage;
import tinqin.zoostorage.persistance.data.Storage;
import tinqin.zoostorage.persistance.repository.StorageRepository;

import java.util.UUID;

@Service
public class DeleteStorageImpl implements DeleteStorage {
    private final StorageRepository storageRepository;

    private final ModelMapper modelMapper;

    public DeleteStorageImpl(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeleteResponse process(DeleteRequest input) {
        Storage storage = storageRepository.getStorageById(UUID.fromString(input.getStorageId()));
        storageRepository.delete(storage);

        return modelMapper.map(storage, DeleteResponse.class);
    }
}
