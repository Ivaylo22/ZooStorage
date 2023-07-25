package tinqin.zoostorage.model.getquantitybyid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQuantityByIdResponse implements OperationResult {
    private String title;
    private String description;
    private Integer quantity;
}
