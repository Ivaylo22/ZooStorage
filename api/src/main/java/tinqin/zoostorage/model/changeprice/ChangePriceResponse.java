package tinqin.zoostorage.model.changeprice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePriceResponse implements OperationResult {
    private String itemId;
    private Double price;
}
