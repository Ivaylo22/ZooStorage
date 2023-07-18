package tinqin.zoostorage.api.model.add_storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStorageRequest implements OperationInput {
    private String storageId;
    private String itemId;
    private Integer quantity;
    private Double price;
}
