package tinqin.zoostorage.storageimpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostorage.model.exportitems.ExportItems;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.UUID;

@Service
public class ExportItemsImpl implements ExportItems {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;

    public ExportItemsImpl(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ExportResponse process(ExportRequest input) {
        Storage storage = storageRepository.getStorageByItemId(UUID.fromString(input.getItemId()));
        if(storage.getQuantity() - input.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cant be less than 0");
        }
        storage.setQuantity(storage.getQuantity() - input.getQuantity());

        storageRepository.save(storage);

        return modelMapper.map(storage, ExportResponse.class);
    }
}
