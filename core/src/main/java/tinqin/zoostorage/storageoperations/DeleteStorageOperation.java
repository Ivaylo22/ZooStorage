package tinqin.zoostorage.storageoperations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.model.deletestorage.DeleteRequest;
import tinqin.zoostorage.model.deletestorage.DeleteResponse;
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
    public DeleteResponse process(DeleteRequest input) {
        Storage storage = storageRepository.getStorageById(UUID.fromString(input.getStorageId()));
        storageRepository.delete(storage);

        return modelMapper.map(storage, DeleteResponse.class);
    }
}
