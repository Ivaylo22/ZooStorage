package tinqin.zoostorage.storageoperations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.model.deletestorage.DeleteStorageRequest;
import tinqin.zoostorage.model.deletestorage.DeleteStorageResponse;
import tinqin.zoostorage.model.deletestorage.DeleteStorage;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.UUID;

@Service
public class DeleteStorageOperation implements DeleteStorage {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;

    public DeleteStorageOperation(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeleteStorageResponse process(DeleteStorageRequest input) {
        Storage storage = storageRepository.getStorageById(UUID.fromString(input.getStorageId()));
        storageRepository.delete(storage);

        return modelMapper.map(storage, DeleteStorageResponse.class);
    }
}
