package tinqin.zoostorage.storageoperations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.model.getinfobyid.GetInfoById;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdRequest;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.getstorage.GetStorageRequest;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;
import tinqin.zoostorage.repository.StorageRepository;
import tinqin.zoostore.ZooStoreRestClient;
import tinqin.zoostore.model.item.getitem.GetItemResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetInfoByIdOperation implements GetInfoById {
    private final ZooStoreRestClient restClient;
    private final StorageRepository storageRepository;
    private final GetStorageOperation getStorage;

    @Override
    public GetInfoByIdResponse process(GetInfoByIdRequest input) {
        UUID itemId = input.getItemId();
        GetItemResponse item = restClient.getItemById(itemId);

        List<Storage> storages =  storageRepository
                .findAllByItemId(itemId);

        List<GetStorageResponse> storageResponses = new ArrayList<>();
        Integer totalQuantity = 0;

        for (Storage storage: storages) {
            GetStorageRequest request = GetStorageRequest
                    .builder()
                    .storageId(storage.getId())
                    .build();

            GetStorageResponse response = getStorage.process(request);
            totalQuantity += storage.getQuantity();
            storageResponses.add(response);
        }

        return GetInfoByIdResponse
        .builder()
        .storages(storageResponses)
        .itemId(itemId)
        .title(item.getTitle())
        .description(item.getDescription())
        .totalQuantity(totalQuantity)
        .build();
    }
}
