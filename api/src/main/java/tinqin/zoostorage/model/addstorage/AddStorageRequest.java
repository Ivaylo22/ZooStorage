package tinqin.zoostorage.model.addstorage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationInput;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStorageRequest implements OperationInput {
    private UUID itemId;
    private Integer quantity;
    private Double price;
}
