package tinqin.zoostorage;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.getstorage.GetStorage;
import tinqin.zoostorage.model.getstorage.GetStorageRequest;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;
import tinqin.zoostorage.repository.StorageRepository;

@Service
@RequiredArgsConstructor
public class GetStorageImpl implements GetStorage {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;

    @Override
    public GetStorageResponse process(GetStorageRequest input) {
        Storage storage = storageRepository.getStorageById(input.getStorageId());
        GetStorageResponse response = new GetStorageResponse();

        response.setItemId(storage.getItemId());
        response.setStorageId(storage.getId());

        return response;
    }
}
