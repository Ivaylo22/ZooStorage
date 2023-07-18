package tinqin.zoostorage.rest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tinqin.zoostorage.api.model.add_storage.AddStorage;
import tinqin.zoostorage.api.model.add_storage.AddStorageRequest;
import tinqin.zoostorage.api.model.add_storage.AddStorageResponse;
import tinqin.zoostorage.api.model.change_price.ChangePrice;
import tinqin.zoostorage.api.model.change_price.ChangePriceRequest;
import tinqin.zoostorage.api.model.change_price.ChangePriceResponse;
import tinqin.zoostorage.api.model.delete_storage.DeleteStorage;
import tinqin.zoostorage.api.model.delete_storage.DeleteRequest;
import tinqin.zoostorage.api.model.delete_storage.DeleteResponse;
import tinqin.zoostorage.api.model.export_items.ExportItems;
import tinqin.zoostorage.api.model.export_items.ExportRequest;
import tinqin.zoostorage.api.model.export_items.ExportResponse;
import tinqin.zoostorage.api.model.import_items.ImportItems;
import tinqin.zoostorage.api.model.import_items.ImportRequest;
import tinqin.zoostorage.api.model.import_items.ImportResponse;


@RestController
@RequestMapping("/storage")
public class StorageController {
    private final AddStorage addStorage;
    private final ChangePrice changePrice;
    private final DeleteStorage deleteStorage;
    private final ExportItems exportItems;
    private final ImportItems importItems;

    public StorageController(AddStorage addStorage, ChangePrice changePrice, DeleteStorage deleteStorage, ExportItems exportItems, ImportItems importItems) {
        this.addStorage = addStorage;
        this.changePrice = changePrice;
        this.deleteStorage = deleteStorage;
        this.exportItems = exportItems;
        this.importItems = importItems;
    }


    @PostMapping("/addStorage")
    public ResponseEntity<AddStorageResponse> addStorage(@RequestBody AddStorageRequest storageDto) {
        AddStorageResponse addedStorage = addStorage.process(storageDto);

        return ResponseEntity.ok(addedStorage);
    }

    @PostMapping("/import")
    public ResponseEntity<ImportResponse> importInStorage(@RequestBody ImportRequest importDto) {
        ImportResponse importResponse = importItems.process(importDto);

        return ResponseEntity.ok(importResponse);
    }

    @PostMapping("/export")
    public ResponseEntity<ExportResponse> exportFromStorage(@RequestBody ExportRequest exportDto) {
        ExportResponse exportResponse = exportItems.process(exportDto);

        return ResponseEntity.ok(exportResponse);
    }

    @PostMapping("/deleteStorage")
    public ResponseEntity<DeleteResponse> deleteStorage(@RequestBody DeleteRequest storageDto) {
        DeleteResponse deletedStorage = deleteStorage.process(storageDto);

        return ResponseEntity.ok(deletedStorage);
    }

    @PostMapping("/changePrice")
    public ResponseEntity<ChangePriceResponse> changePrice(@RequestBody ChangePriceRequest priceDto) {
        ChangePriceResponse changedPrize = changePrice.process(priceDto);

        return ResponseEntity.ok(changedPrize);
    }
}
