package tinqin.zoostorage.model.exportitems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExportResponse implements OperationResult {
    private String itemId;
    private Double finalPrice;
}
