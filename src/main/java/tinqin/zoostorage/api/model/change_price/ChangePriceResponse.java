package tinqin.zoostorage.api.model.change_price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePriceResponse implements OperationResult {
    private String itemId;
    private Double price;
}
