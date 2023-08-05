package tinqin.zoostorage.storageimpl;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddSaleImpl implements AddSale {
    private final StorageRepository storageRepository;
    private final SaleRepository saleRepository;
    private final ExportItems exportItems;
    
    @Override
    public AddSaleResponse process(AddSaleRequest input) {
        Integer userId = input.getUserId();
        Map<UUID, Integer> items = input.getItems();
        List<Sale> sales = new ArrayList<>();

        items.keySet().forEach(itemId -> {
            Integer quantity = items.get(itemId);
            Storage storage = storageRepository.getStorageByItemId(itemId);
            ExportRequest exportRequest = new ExportRequest(itemId.toString(), quantity);
            exportItems.process(exportRequest);

            final Sale sale = Sale.builder()
                    .itemId(itemId)
                    .price(storage.getPrice() * quantity)
                    .quantity(quantity)
                    .userId(userId)
                    .build();
            sales.add(sale);
        });

        saleRepository.saveAll(sales);
        List<UUID> saleIds = sales.stream()
                .map(Sale::getId)
                .collect(Collectors.toList());

        return AddSaleResponse
                .builder()
                .saleIds(saleIds)
                .build();
    }
}
