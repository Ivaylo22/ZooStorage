package tinqin.zoostorage.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinqin.zoostorage.model.addstorage.AddStorage;
import tinqin.zoostorage.model.addstorage.AddStorageRequest;
import tinqin.zoostorage.model.addstorage.AddStorageResponse;
import tinqin.zoostorage.model.changeprice.ChangePrice;
import tinqin.zoostorage.model.changeprice.ChangePriceRequest;
import tinqin.zoostorage.model.changeprice.ChangePriceResponse;
import tinqin.zoostorage.model.deletestorage.DeleteRequest;
import tinqin.zoostorage.model.deletestorage.DeleteResponse;
import tinqin.zoostorage.model.deletestorage.DeleteStorage;
import tinqin.zoostorage.model.exportitems.ExportItems;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostorage.model.getquantitybyid.GetQuantityById;
import tinqin.zoostorage.model.getquantitybyid.GetQuantityByIdRequest;
import tinqin.zoostorage.model.getquantitybyid.GetQuantityByIdResponse;
import tinqin.zoostorage.model.importitems.ImportItems;
import tinqin.zoostorage.model.importitems.ImportRequest;
import tinqin.zoostorage.model.importitems.ImportResponse;

import java.util.UUID;

@RestController
@RequestMapping("/storage")
public class StorageController {
    private final AddStorage addStorage;
    private final ChangePrice changePrice;
    private final DeleteStorage deleteStorage;
    private final ExportItems exportItems;
    private final ImportItems importItems;
    private final GetQuantityById getQuantity;

    public StorageController(AddStorage addStorage, ChangePrice changePrice, DeleteStorage deleteStorage, ExportItems exportItems, ImportItems importItems, GetQuantityById getQuantity) {
        this.addStorage = addStorage;
        this.changePrice = changePrice;
        this.deleteStorage = deleteStorage;
        this.exportItems = exportItems;
        this.importItems = importItems;
        this.getQuantity = getQuantity;
    }


    @GetMapping("/quantityById/{itemId}")
    public ResponseEntity<GetQuantityByIdResponse> getQuantity(@PathVariable UUID itemId) {
        GetQuantityByIdRequest request = new GetQuantityByIdRequest();
        request.setItemId(itemId);
        GetQuantityByIdResponse response = getQuantity.process(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/addStorage")
    public ResponseEntity<AddStorageResponse> addStorage(@RequestBody AddStorageRequest storageDto) {
        AddStorageResponse addedStorage = addStorage.process(storageDto);

        return ResponseEntity.ok(addedStorage);
    }

    @PutMapping("/import")
    public ResponseEntity<ImportResponse> importInStorage(@RequestBody ImportRequest importDto) {
        ImportResponse importResponse = importItems.process(importDto);

        return ResponseEntity.ok(importResponse);
    }

    @PutMapping("/export")
    public ResponseEntity<ExportResponse> exportFromStorage(@RequestBody ExportRequest exportDto) {
        ExportResponse exportResponse = exportItems.process(exportDto);

        return ResponseEntity.ok(exportResponse);
    }

    @PostMapping("/deleteStorage")
    public ResponseEntity<DeleteResponse> deleteStorage(@RequestBody DeleteRequest storageDto) {
        DeleteResponse deletedStorage = deleteStorage.process(storageDto);

        return ResponseEntity.ok(deletedStorage);
    }

    @PutMapping("/changePrice")
    public ResponseEntity<ChangePriceResponse> changePrice(@RequestBody ChangePriceRequest priceDto) {
        ChangePriceResponse changedPrize = changePrice.process(priceDto);

        return ResponseEntity.ok(changedPrize);
    }
}
