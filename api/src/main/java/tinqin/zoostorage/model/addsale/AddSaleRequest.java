package tinqin.zoostorage.model.addsale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationInput;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddSaleRequest implements OperationInput {
    private Integer userId;
    private Map<UUID, Integer> items;
}
