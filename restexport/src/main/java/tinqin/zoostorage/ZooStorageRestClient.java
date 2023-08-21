package tinqin.zoostorage;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import tinqin.zoostorage.model.addsale.AddSaleRequest;
import tinqin.zoostorage.model.addsale.AddSaleResponse;
import tinqin.zoostorage.model.addstorage.AddStorageRequest;
import tinqin.zoostorage.model.addstorage.AddStorageResponse;
import tinqin.zoostorage.model.changeprice.ChangePriceRequest;
import tinqin.zoostorage.model.changeprice.ChangePriceResponse;
import tinqin.zoostorage.model.checkstoragebyitem.CheckStorageByItemResponse;
import tinqin.zoostorage.model.deletestorage.DeleteStorageRequest;
import tinqin.zoostorage.model.deletestorage.DeleteStorageResponse;
import tinqin.zoostorage.model.exportitems.ExportRequest;
import tinqin.zoostorage.model.exportitems.ExportResponse;
import tinqin.zoostorage.model.getinfobyid.GetInfoByIdResponse;
import tinqin.zoostorage.model.getstorage.GetStorageRequest;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;
import tinqin.zoostorage.model.importitems.ImportRequest;
import tinqin.zoostorage.model.importitems.ImportResponse;

import java.util.UUID;

@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface ZooStorageRestClient {
    @RequestLine("POST /storage/addSale")
    AddSaleResponse addSale(AddSaleRequest request);

    @RequestLine("GET /storage/checkStorageByItem/{itemId}")
    CheckStorageByItemResponse checkStorageByItemId(@Param("itemId") UUID itemId);

    @RequestLine("GET /storage/getInfo/{itemId}")
    GetInfoByIdResponse getInfoByItemId(@Param("itemId") UUID itemId);

    @RequestLine("POST /storage/getStorage")
    GetStorageResponse getStorage(GetStorageRequest request);

    @RequestLine("GET /storage/infoById/{itemId}")
    GetInfoByIdResponse getQuantity(@Param("itemId") UUID itemId);

    @RequestLine("POST /storage/addStorage")
    AddStorageResponse addStorage(@Param AddStorageRequest storageDto);

    @RequestLine("PUT /storage/import")
    ImportResponse importInStorage(@Param ImportRequest importDto);

    @RequestLine("PUT /storage/export")
    ExportResponse exportFromStorage(@Param ExportRequest exportDto);

    @RequestLine("POST /storage/deleteStorage")
    DeleteStorageResponse deleteStorage(@Param DeleteStorageRequest storageDto);

    @RequestLine("PUT /storage/changePrice")
    ChangePriceResponse changePrice(@Param ChangePriceRequest priceDto);
}
