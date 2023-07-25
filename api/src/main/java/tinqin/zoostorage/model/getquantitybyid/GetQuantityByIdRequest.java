package tinqin.zoostorage.model.getquantitybyid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationInput;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQuantityByIdRequest implements OperationInput {
    private UUID itemId;
}
