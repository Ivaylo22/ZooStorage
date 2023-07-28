package tinqin.zoostorage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinqin.zoostorage.model.addstorage.AddStorage;
import tinqin.zoostorage.model.addstorage.AddStorageRequest;
import tinqin.zoostorage.model.addstorage.AddStorageResponse;
import tinqin.zoostorage.model.changeprice.ChangePrice;
import tinqin.zoostorage.model.changeprice.ChangePriceRequest;
import tinqin.zoostorage.model.changeprice.ChangePriceResponse;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItem;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItemRequest;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItemResponse;
import tinqin.zoostorage.model.deletestorage.DeleteRequest;
import tinqin.zoostorage.model.deletestorage.DeleteResponse;
import tinqin.zoostorage.model.deletestorage.DeleteStorage;
import tinqin.zoostorage.model.exportitems.ExportItems;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostorage.model.getinfobyid.GetInfoById;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdRequest;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostorage.model.getstorage.GetStorage;
import tinqin.zoostorage.model.getstorage.GetStorageRequest;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;
import tinqin.zoostorage.model.importitems.ImportItems;
import tinqin.zoostorage.model.importitems.ImportRequest;
import tinqin.zoostorage.model.importitems.ImportResponse;

import java.util.UUID;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {
    private final AddStorage addStorage;
    private final ChangePrice changePrice;
    private final DeleteStorage deleteStorage;
    private final ExportItems exportItems;
    private final ImportItems importItems;
    private final GetInfoById getQuantity;
    private final GetStorage getStorage;
    private final GetInfoById getInfoById;
    private final CheckStorageByItem checkStorageByItem;

    @GetMapping("checkStorageByItem/{itemId}")
    public ResponseEntity<CheckStorageByItemResponse> checkStorageByItemId(@PathVariable UUID itemId) {
        CheckStorageByItemRequest request = new CheckStorageByItemRequest(itemId);
        CheckStorageByItemResponse response = checkStorageByItem.process(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getInfo/{itemId}")
    public ResponseEntity<GetInfoByIdResponse> getInfoByItemId(@PathVariable UUID itemId) {
        GetInfoByIdRequest request = new GetInfoByIdRequest(itemId);
        GetInfoByIdResponse response = getInfoById.process(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{storageId}")
    public ResponseEntity<GetStorageResponse> getStorage(@PathVariable UUID storageId) {
        GetStorageRequest request = new GetStorageRequest();
        request.setStorageId(storageId);
        GetStorageResponse response = getStorage.process(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/quantityById/{itemId}")
    public ResponseEntity<GetInfoByIdResponse> getQuantity(@PathVariable UUID itemId) {
        GetInfoByIdRequest request = new GetInfoByIdRequest();
        request.setItemId(itemId);
        GetInfoByIdResponse response = getQuantity.process(request);

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
