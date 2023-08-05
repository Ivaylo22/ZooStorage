package tinqin.zoostorage.storageimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.model.getinfobyid.GetInfoById;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdRequest;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.repository.StorageRepository;
import tinqin.zoostore.ZooStoreRestClient;
import tinqin.zoostore.model.item.getitem.GetItemResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetInfoByIdImpl implements GetInfoById {
    private final ZooStoreRestClient restClient;
    private final StorageRepository storageRepository;

    @Override
    public GetInfoByIdResponse process(GetInfoByIdRequest input) {
        UUID itemId = input.getItemId();
        GetItemResponse item = restClient.getItemById(itemId);

        Storage storage =  storageRepository
                .findByItemId(itemId);

        return GetInfoByIdResponse
        .builder()
        .title(item.getTitle())
        .description(item.getDescription())
        .quantity(storage.getQuantity())
        .price(storage.getPrice())
        .build();
    }
}
