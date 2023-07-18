package tinqin.zoostorage.api.model.add_storage;

import org.springframework.stereotype.Service;
import tinqin.zoostorage.api.operations.OperationProcessor;

@Service
public interface AddStorage extends OperationProcessor<AddStorageRequest, AddStorageResponse> {
}
