package tinqin.zoostorage.model.checkstoragebyitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckStorageByItemResponse implements OperationResult {
    private Boolean isInStorage;
}
