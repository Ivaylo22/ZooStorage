package tinqin.zoostorage.storageoperations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.data.Sale;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.exceptions.NotEnoughQuantityException;
import tinqin.zoostorage.model.addsale.AddSale;
import tinqin.zoostorage.model.addsale.AddSaleRequest;
import tinqin.zoostorage.model.addsale.AddSaleResponse;
import tinqin.zoostorage.model.exportitems.ExportItems;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostorage.model.getinfobyid.GetInfoById;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdRequest;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostorage.repository.SaleRepository;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddSaleOperation implements AddSale {
    private final StorageRepository storageRepository;
    private final SaleRepository saleRepository;

    @Override
    public AddSaleResponse process(AddSaleRequest input) {
        Integer userId = input.getUserId();

        Sale sale = Sale.builder()
                .userId(userId)
                .price(input.getPrice() - input.getSavedMoney())
                .savedMoney(input.getSavedMoney())
                .build();

        saleRepository.save(sale);

        return AddSaleResponse.builder()
                .price(sale.getPrice())
                .savedPrice(sale.getSavedMoney())
                .build();
    }
}
