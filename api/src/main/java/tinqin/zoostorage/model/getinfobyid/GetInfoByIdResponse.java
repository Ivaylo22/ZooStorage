package tinqin.zoostorage.model.getinfobyid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.model.getstorage.GetStorageResponse;
import tinqin.zoostorage.operations.OperationResult;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetInfoByIdResponse implements OperationResult {
    private List<GetStorageResponse> storages;
    private Integer totalQuantity;
    private UUID itemId;
    private String title;
    private String description;
}
