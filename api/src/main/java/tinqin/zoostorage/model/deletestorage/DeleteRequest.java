package tinqin.zoostorage.model.deletestorage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRequest implements OperationInput {
    private String storageId;
}
