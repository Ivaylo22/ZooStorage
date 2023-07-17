package tinqin.zoostorage.api.model.import_items.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportResponse implements OperationResult {
    private String storageId;
    private String itemId;
    private Integer quantity;
}
