package tinqin.zoostorage.storageoperations;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.exceptions.NotEnoughQuantityException;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostorage.model.exportitems.ExportItems;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.getinfobyid.GetInfoById;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdRequest;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExportItemsOperation implements ExportItems {
    private final StorageRepository storageRepository;
    private final ModelMapper modelMapper;
    private final GetInfoById getInfoById;


    @Override
    public ExportResponse process(ExportRequest input) throws Exception {
        GetInfoByIdRequest getInfoRequest = GetInfoByIdRequest
                .builder()
                .itemId(UUID.fromString(input.getItemId()))
                .build();
        GetInfoByIdResponse fullInfo = getInfoById.process(getInfoRequest);

        if(fullInfo.getTotalQuantity() < input.getQuantity()) {
            throw new NotEnoughQuantityException(fullInfo.getTitle());
        }

        Storage storage = storageRepository
                .getStorageByItemIdAndCity(UUID.fromString(input.getItemId()), input.getCity());
        if(storage.getQuantity() - input.getQuantity() < 0) {
             storage.setQuantity(0);
        }
        storage.setQuantity(storage.getQuantity() - input.getQuantity());

        storageRepository.save(storage);

        return modelMapper.map(storage, ExportResponse.class);
    }
}
