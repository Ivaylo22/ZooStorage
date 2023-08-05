package tinqin.zoostorage.model.getinfobyid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetInfoByIdResponse implements OperationResult {
    private String title;
    private String description;
    private Integer quantity;
    private Double price;
}
