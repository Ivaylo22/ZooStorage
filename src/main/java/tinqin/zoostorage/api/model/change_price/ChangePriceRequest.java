package tinqin.zoostorage.api.model.change_price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.api.operations.OperationInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePriceRequest implements OperationInput {
    private String itemId;
    private Double price;
}
