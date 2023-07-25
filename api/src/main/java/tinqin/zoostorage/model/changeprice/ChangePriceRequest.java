package tinqin.zoostorage.model.changeprice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePriceRequest implements OperationInput {
    private String itemId;
    private Double price;
}
