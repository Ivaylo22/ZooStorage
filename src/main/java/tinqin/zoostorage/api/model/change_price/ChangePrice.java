package tinqin.zoostorage.api.model.change_price;

import org.springframework.stereotype.Service;
import tinqin.zoostorage.api.operations.OperationProcessor;

@Service
public interface ChangePrice  extends OperationProcessor<ChangePriceRequest, ChangePriceResponse> {
}
