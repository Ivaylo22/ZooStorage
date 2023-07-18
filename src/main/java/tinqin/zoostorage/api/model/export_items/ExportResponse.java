package tinqin.zoostorage.api.model.export_items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportResponse implements OperationResult {
    private String storageId;
    private String itemId;
    private Integer quantity;
}
