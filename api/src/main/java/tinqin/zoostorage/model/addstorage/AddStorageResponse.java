package tinqin.zoostorage.model.addstorage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStorageResponse implements OperationResult {
    private UUID storageId;
    private UUID itemId;
    private Integer quantity;
    private Double price;
}
