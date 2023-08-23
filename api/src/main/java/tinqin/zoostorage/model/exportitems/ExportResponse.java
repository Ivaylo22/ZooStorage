package tinqin.zoostorage.model.exportitems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportResponse implements OperationResult {
    private String itemId;
}
