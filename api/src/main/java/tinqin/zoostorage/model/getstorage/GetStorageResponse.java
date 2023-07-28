package tinqin.zoostorage.model.getstorage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStorageResponse implements OperationResult {
    private UUID storageId;
    private UUID itemId;
}
