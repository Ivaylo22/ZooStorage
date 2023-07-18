package tinqin.zoostorage.core;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.api.model.import_items.ImportRequest;
import tinqin.zoostorage.api.model.import_items.ImportResponse;
import tinqin.zoostorage.api.model.import_items.ImportItems;
import tinqin.zoostorage.persistance.data.Storage;
import tinqin.zoostorage.persistance.repository.StorageRepository;

import java.util.UUID;

@Service
public class ImportItemsImpl implements ImportItems {
    private final StorageRepository storageRepository;

    private final ModelMapper modelMapper;

    public ImportItemsImpl(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ImportResponse process(ImportRequest input) {
        Storage storage = storageRepository.getStorageByItemId(UUID.fromString(input.getItemId()));
        storage.setQuantity(storage.getQuantity() + input.getQuantity());

        return modelMapper.map(storage, ImportResponse.class);
    }
}
