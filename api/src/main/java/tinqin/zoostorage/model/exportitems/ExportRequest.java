package tinqin.zoostorage.model.exportitems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExportRequest implements OperationInput {
    private String itemId;
    private Integer quantity;
    private String city;
}
