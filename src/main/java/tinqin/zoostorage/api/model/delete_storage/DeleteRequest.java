package tinqin.zoostorage.api.model.delete_storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRequest implements OperationInput {
    private String storageId;
}
