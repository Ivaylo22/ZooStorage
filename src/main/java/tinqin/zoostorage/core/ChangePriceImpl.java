package tinqin.zoostorage.core;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.api.model.change_price.ChangePriceRequest;
import tinqin.zoostorage.api.model.change_price.ChangePriceResponse;
import tinqin.zoostorage.api.model.change_price.ChangePrice;
import tinqin.zoostorage.persistance.data.Storage;
import tinqin.zoostorage.persistance.repository.StorageRepository;

import java.util.UUID;

@Service
public class ChangePriceImpl implements ChangePrice {
    private final StorageRepository storageRepository;

    private final ModelMapper modelMapper;

    public ChangePriceImpl(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ChangePriceResponse process(ChangePriceRequest input) {
        Storage storage = storageRepository.getStorageByItemId(UUID.fromString(input.getItemId()));
        storage.setPrice(input.getPrice());

        return new ChangePriceResponse(input.getItemId(), input.getPrice());
    }
}
