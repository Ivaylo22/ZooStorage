package tinqin.zoostorage.storageimpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.addstorage.AddStorage;
import tinqin.zoostorage.model.addstorage.AddStorageRequest;
import tinqin.zoostorage.model.addstorage.AddStorageResponse;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.UUID;

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
        storageRepository.save(storage);

        return modelMapper.map(storage, AddStorageResponse.class);
    }
}
