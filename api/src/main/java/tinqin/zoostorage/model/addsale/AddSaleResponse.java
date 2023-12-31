package tinqin.zoostorage.model.addsale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddSaleResponse implements OperationResult {
    private Double price;
    private Double savedPrice;
}
