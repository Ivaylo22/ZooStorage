package tinqin.zoostorage.storageimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItem;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItemRequest;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItemResponse;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckStorageByItemImpl implements CheckStorageByItem {
    private final StorageRepository storageRepository;

    @Override
    public CheckStorageByItemResponse process(CheckStorageByItemRequest input) {
        UUID itemId = input.getItemId();

        boolean isInStorage = storageRepository.existsByItemId(itemId);
        CheckStorageByItemResponse response = CheckStorageByItemResponse
                .builder()
                .isInStorage(isInStorage)
                .build();

        return response;
    }
}
