package tinqin.zoostorage.storageoperations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinqin.zoostorage.data.Sale;
import tinqin.zoostorage.data.Storage;
import tinqin.zoostorage.model.addsale.AddSale;
import tinqin.zoostorage.model.addsale.AddSaleRequest;
import tinqin.zoostorage.model.addsale.AddSaleResponse;
import tinqin.zoostorage.model.exportitems.ExportItems;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.repository.SaleRepository;
import tinqin.zoostorage.repository.StorageRepository;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddSaleOperation implements AddSale {
    private final StorageRepository storageRepository;
    private final SaleRepository saleRepository;
    private final ExportItems exportItems;

    @Override
    public AddSaleResponse process(AddSaleRequest input) {
        Integer userId = input.getUserId();
        Map<UUID, Integer> items = input.getItems();
        double actualPrice = items.entrySet().stream()
                .mapToDouble(entry -> {
                    UUID itemId = entry.getKey();
                    Integer quantity = entry.getValue();
                    Storage storage = storageRepository.getStorageByItemId(itemId);
                    ExportRequest exportRequest = new ExportRequest(itemId.toString(), quantity);
                    exportItems.process(exportRequest);

                    return storage.getPrice() * quantity;
                })
                .sum();

        Sale sale = Sale.builder()
                .userId(userId)
                .price(actualPrice - input.getSavedMoney())
                .savedMoney(input.getSavedMoney())
                .build();

        saleRepository.save(sale);

        return AddSaleResponse.builder()
                .price(sale.getPrice())
                .savedPrice(sale.getSavedMoney())
                .build();
    }
}
