package tinqin.zoostorage;

import org.springframework.stereotype.Service;
import tinqin.zoostorage.model.getquantitybyid.GetQuantityById;
import tinqin.zoostorage.model.getquantitybyid.GetQuantityByIdRequest;
import tinqin.zoostorage.model.getquantitybyid.GetQuantityByIdResponse;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.repository.StorageRepository;
import tinqin.zoostore.ZooStoreRestClient;
import tinqin.zoostore.model.item.getitem.GetItemResponse;

import java.util.UUID;

@Service
public class GetQuantityByIdImpl implements GetQuantityById {
    private final ZooStoreRestClient restClient;
    private final StorageRepository storageRepository;

    public GetQuantityByIdImpl(ZooStoreRestClient restClient, StorageRepository storageRepository) {
        this.restClient = restClient;
        this.storageRepository = storageRepository;
    }

    @Override
    public GetQuantityByIdResponse process(GetQuantityByIdRequest input) {
        UUID itemId = input.getItemId();
        GetItemResponse item = restClient.getItemById(itemId);

        Storage storage =  storageRepository
                .getStorageByItemId(itemId);

        GetQuantityByIdResponse response = new GetQuantityByIdResponse();
        response.setTitle(item.getTitle());
        response.setDescription(item.getDescription());
        response.setQuantity(storage.getQuantity());

        return response;
    }
}
