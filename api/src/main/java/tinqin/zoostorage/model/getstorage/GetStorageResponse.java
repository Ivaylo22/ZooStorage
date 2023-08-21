package tinqin.zoostorage.model.getstorage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tinqin.zoostorage.operations.OperationResult;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetStorageResponse implements OperationResult {
    private UUID storageId;
    private UUID itemId;
    private Double price;
    private Integer quantity;
    private String city;
}
