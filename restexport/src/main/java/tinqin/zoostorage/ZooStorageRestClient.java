package tinqin.zoostorage;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tinqin.zoostorage.model.addstorage.AddStorageRequest;
import tinqin.zoostorage.model.addstorage.AddStorageResponse;
import tinqin.zoostorage.model.changeprice.ChangePriceRequest;
import tinqin.zoostorage.model.changeprice.ChangePriceResponse;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItemRequest;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItemResponse;
import tinqin.zoostorage.model.deletestorage.DeleteRequest;
import tinqin.zoostorage.model.deletestorage.DeleteResponse;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdRequest;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;
import tinqin.zoostorage.model.importitems.ImportRequest;
import tinqin.zoostorage.model.importitems.ImportResponse;

import java.util.UUID;

@Headers({"Content-Type: application/json"})
public interface ZooStorageRestClient {
    @RequestLine("GET /storage/checkStorageByItem/{itemId}")
    CheckStorageByItemResponse checkStorageByItemId(@Param("itemId") UUID itemId);

    @RequestLine("GET /storage/getInfo/{itemId}")
    GetInfoByIdResponse getInfoByItemId(@Param("itemId") UUID itemId);

    @RequestLine("GET /storage/{storageId}")
    public GetStorageResponse getStorage(@Param("storageId") UUID storageId);

    @RequestLine("GET /storage/infoById/{itemId}")
    GetInfoByIdResponse getQuantity(@Param("itemId") UUID itemId);

    @RequestLine("POST /storage/addStorage")
    AddStorageResponse addStorage(@Param AddStorageRequest storageDto);

    @RequestLine("PUT /storage/import")
    ImportResponse importInStorage(@Param ImportRequest importDto);

    @RequestLine("PUT /storage/export")
    public ExportResponse exportFromStorage(@Param ExportRequest exportDto);

    @RequestLine("POST /storage/deleteStorage")
    public DeleteResponse deleteStorage(@Param DeleteRequest storageDto);

    @RequestLine("PUT /storage/changePrice")
    public ChangePriceResponse changePrice(@Param ChangePriceRequest priceDto);
}
