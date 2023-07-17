package tinqin.zoostorage.api.model.export_items.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportRequest implements OperationInput {
    private String itemId;
    private Integer quantity;
}
