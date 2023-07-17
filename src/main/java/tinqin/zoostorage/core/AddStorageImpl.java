package tinqin.zoostorage.core;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.api.model.add_storage.AddStorage;
import tinqin.zoostorage.persistance.data.Storage;
import tinqin.zoostorage.api.model.add_storage.input.AddStorageRequest;
import tinqin.zoostorage.api.model.add_storage.output.AddStorageResponse;
import tinqin.zoostorage.persistance.repository.StorageRepository;

@Service
public class AddStorageImpl implements AddStorage {

    private final StorageRepository storageRepository;

    private final ModelMapper modelMapper;

    public AddStorageImpl(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddStorageResponse process(AddStorageRequest storageDto) {
        Storage storage = modelMapper.map(storageDto, Storage.class);
        storage.setPrice(0.0);
        storage.setQuantity(0);
        return modelMapper.map(storage, AddStorageResponse.class);
    }
}
