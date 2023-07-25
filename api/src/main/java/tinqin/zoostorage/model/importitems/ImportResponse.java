package tinqin.zoostorage.model.importitems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportResponse implements OperationResult {
    private String storageId;
    private String itemId;
    private Integer quantity;
}
