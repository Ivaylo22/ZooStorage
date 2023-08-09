package tinqin.zoostorage.model.addsale;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import tinqin.zoostorage.operations.OperationInput;

import java.util.Map;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddSaleRequest implements OperationInput {
    private Integer userId;
    private Map<UUID, Integer> items;
    private Double price;
    private Double savedMoney;
}
