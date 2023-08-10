package tinqin.zoostorage.storageoperations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.model.changeprice.ChangePriceRequest;
import tinqin.zoostorage.model.changeprice.ChangePriceResponse;
import tinqin.zoostorage.model.changeprice.ChangePrice;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangePriceOperation implements ChangePrice {
    private final StorageRepository storageRepository;


    @Override
    public ChangePriceResponse process(ChangePriceRequest input) {
        Storage storage = storageRepository.getStorageByItemId(UUID.fromString(input.getItemId()));
        storage.setPrice(input.getPrice());

        storageRepository.save(storage);
        ChangePriceResponse response = ChangePriceResponse.builder()
                .itemId(input.getItemId())
                .price(input.getPrice())
                .build();
        return response;
    }
}
