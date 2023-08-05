package tinqin.zoostorage.storageimpl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.getstorage.GetStorage;
import tinqin.zoostorage.model.getstorage.GetStorageRequest;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;
import tinqin.zoostorage.repository.StorageRepository;
import tinqin.zoostore.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class GetStorageImpl implements GetStorage {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;

    @Override
    public GetStorageResponse process(GetStorageRequest input) {
        Storage storage = storageRepository
                .findById(input.getStorageId())
                .orElseThrow(() -> new ResourceNotFoundException("Item", input.getStorageId().toString()));

        return GetStorageResponse
                .builder()
                .itemId(storage.getItemId())
                .storageId(storage.getId())
                .build();
    }
}
