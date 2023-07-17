package tinqin.zoostorage.api.model.add_storage.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStorageResponse implements OperationResult {
    private String storageId;
    private String itemId;
    private Integer quantity;
    private Double price;
}
