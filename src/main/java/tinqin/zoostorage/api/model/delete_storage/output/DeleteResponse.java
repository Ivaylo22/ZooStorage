package tinqin.zoostorage.api.model.delete_storage.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteResponse implements OperationResult {
    private String storageId;
}
