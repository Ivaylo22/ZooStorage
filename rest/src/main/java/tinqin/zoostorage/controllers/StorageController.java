package tinqin.zoostorage.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinqin.zoostorage.model.addsale.AddSale;
import tinqin.zoostorage.model.addsale.AddSaleRequest;
import tinqin.zoostorage.model.addsale.AddSaleResponse;
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
    private final AddSale addSale;

    @Operation(summary = "Sale items", description = "Create sale")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale created")
    })
    @PostMapping("saleItems")
    public ResponseEntity<AddSaleResponse> addSale(@RequestBody AddSaleRequest request) {
        AddSaleResponse response = addSale.process(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Check if storage is available", description = "Check if there is such a storage by its itemId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "True/false")
    })
    @GetMapping("checkStorageByItem/{itemId}")
    public ResponseEntity<CheckStorageByItemResponse> checkStorageByItemId(@PathVariable UUID itemId) {
        CheckStorageByItemRequest request = new CheckStorageByItemRequest(itemId);
        CheckStorageByItemResponse response = checkStorageByItem.process(request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get storage info by itemId", description = "Get full storage information from its itemId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "Item/Storage not found"),
    })
    @GetMapping("/getInfo/{itemId}")
    public ResponseEntity<GetInfoByIdResponse> getInfoByItemId(@PathVariable UUID itemId) {
        GetInfoByIdRequest request = new GetInfoByIdRequest(itemId);
        GetInfoByIdResponse response = getInfoById.process(request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get storage info by id", description = "Find storage with by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "Storage not found"),
    })
    @GetMapping("/{storageId}")
    public ResponseEntity<GetStorageResponse> getStorage(@PathVariable UUID storageId) {
        GetStorageRequest request = new GetStorageRequest();
        request.setStorageId(storageId);
        GetStorageResponse response = getStorage.process(request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get item's quantity", description = "Get item's quantity in the storage by item's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "Item not found"),
    })
    @GetMapping("/quantityById/{itemId}")
    public ResponseEntity<GetInfoByIdResponse> getQuantity(@PathVariable UUID itemId) {
        GetInfoByIdRequest request = new GetInfoByIdRequest();
        request.setItemId(itemId);
        GetInfoByIdResponse response = getQuantity.process(request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add storage", description = "Add new storage in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added")
    })
    @PostMapping("/addStorage")
    public ResponseEntity<AddStorageResponse> addStorage(@RequestBody AddStorageRequest storageDto) {
        AddStorageResponse addedStorage = addStorage.process(storageDto);

        return ResponseEntity.ok(addedStorage);
    }

    @Operation(summary = "Add item quantity", description = "Add extra quantity in particular item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "401", description = "Item not found"),

    })
    @PutMapping("/import")
    public ResponseEntity<ImportResponse> importInStorage(@RequestBody ImportRequest importDto) {
        ImportResponse importResponse = importItems.process(importDto);

        return ResponseEntity.ok(importResponse);
    }

    @Operation(summary = "Remove item quantity", description = "Remove quantity in particular item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully removed"),
            @ApiResponse(responseCode = "401", description = "Item not found"),

    })
    @PutMapping("/export")
    public ResponseEntity<ExportResponse> exportFromStorage(@RequestBody ExportRequest exportDto) {
        ExportResponse exportResponse = exportItems.process(exportDto);

        return ResponseEntity.ok(exportResponse);
    }

    @Operation(summary = "Delete storage", description = "Delete storage from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "Storage not found"),

    })
    @PostMapping("/deleteStorage")
    public ResponseEntity<DeleteResponse> deleteStorage(@RequestBody DeleteRequest storageDto) {
        DeleteResponse deletedStorage = deleteStorage.process(storageDto);

        return ResponseEntity.ok(deletedStorage);
    }

    @Operation(summary = "Change price", description = "Change storage's price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully changed "),
            @ApiResponse(responseCode = "401", description = "Storage not found"),

    })
    @PutMapping("/changePrice")
    public ResponseEntity<ChangePriceResponse> changePrice(@RequestBody ChangePriceRequest priceDto) {
        ChangePriceResponse changedPrize = changePrice.process(priceDto);

        return ResponseEntity.ok(changedPrize);
    }
}
