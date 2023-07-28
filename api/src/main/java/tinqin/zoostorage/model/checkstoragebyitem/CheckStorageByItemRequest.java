package tinqin.zoostorage.model.checkstoragebyitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationInput;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckStorageByItemRequest implements OperationInput {
    private UUID itemId;
}
