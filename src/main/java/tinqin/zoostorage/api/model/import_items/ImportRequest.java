package tinqin.zoostorage.api.model.import_items;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportRequest implements OperationInput {
    private String itemId;
    private Integer quantity;
}
