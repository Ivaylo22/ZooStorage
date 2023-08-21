package tinqin.zoostorage.model.importitems;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportRequest implements OperationInput {
    private String itemId;
    private Integer quantity;
    private String city;
}
