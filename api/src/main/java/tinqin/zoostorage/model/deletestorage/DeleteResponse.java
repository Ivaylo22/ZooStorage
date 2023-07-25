package tinqin.zoostorage.model.deletestorage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteResponse implements OperationResult {
    private String storageId;
}
