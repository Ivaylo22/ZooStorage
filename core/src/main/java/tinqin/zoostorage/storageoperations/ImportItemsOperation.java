package tinqin.zoostorage.storageoperations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.model.importitems.ImportRequest;
import tinqin.zoostorage.model.importitems.ImportResponse;
import tinqin.zoostorage.model.importitems.ImportItems;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.UUID;

@Service
public class ImportItemsOperation implements ImportItems {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;

    public ImportItemsOperation(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ImportResponse process(ImportRequest input) {
        Storage storage = storageRepository.getStorageByItemIdAndCity(UUID.fromString(input.getItemId()), input.getCity());
        storage.setQuantity(storage.getQuantity() + input.getQuantity());

        storageRepository.save(storage);

        return modelMapper.map(storage, ImportResponse.class);
    }
}
