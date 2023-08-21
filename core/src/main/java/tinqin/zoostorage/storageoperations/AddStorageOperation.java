package tinqin.zoostorage.storageoperations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.addstorage.AddStorage;
import tinqin.zoostorage.model.addstorage.AddStorageRequest;
import tinqin.zoostorage.model.addstorage.AddStorageResponse;
import tinqin.zoostorage.repository.StorageRepository;

@Service
public class AddStorageOperation implements AddStorage {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;

    public AddStorageOperation(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AddStorageResponse process(AddStorageRequest storageDto) {
        Storage storage = modelMapper.map(storageDto, Storage.class);

        if(storageRepository.existsByItemId(storageDto.getItemId()) && storageRepository.existsByCity(storageDto.getCity())) {
            Storage currentStorage = storageRepository.getStorageByItemId(storageDto.getItemId());
            Integer currentQuantity = currentStorage.getQuantity();
            currentStorage.setQuantity(currentQuantity + storageDto.getQuantity());
            currentStorage.setPrice(storageDto.getPrice());
            storage = currentStorage;
        }
        storageRepository.save(storage);

        return modelMapper.map(storage, AddStorageResponse.class);
    }
}
