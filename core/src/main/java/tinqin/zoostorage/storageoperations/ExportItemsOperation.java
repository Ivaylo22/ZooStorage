package tinqin.zoostorage.storageoperations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostorage.model.exportitems.ExportItems;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.UUID;

@Service
public class ExportItemsOperation implements ExportItems {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;

    public ExportItemsOperation(StorageRepository storageRepository, ModelMapper modelMapper) {
        this.storageRepository = storageRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ExportResponse process(ExportRequest input) {
        Storage storage = storageRepository
                .getStorageByItemIdAAndCity(UUID.fromString(input.getItemId()), input.getCity());
        if(storage.getQuantity() - input.getQuantity() < 0) {
             storage.setQuantity(0);
        }
        storage.setQuantity(storage.getQuantity() - input.getQuantity());

        storageRepository.save(storage);

        return modelMapper.map(storage, ExportResponse.class);
    }
}
