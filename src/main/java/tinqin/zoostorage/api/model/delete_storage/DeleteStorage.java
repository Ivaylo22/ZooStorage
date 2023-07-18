package tinqin.zoostorage.api.model.delete_storage;

import org.springframework.stereotype.Service;
import tinqin.zoostorage.api.operations.OperationProcessor;

@Service
public interface DeleteStorage extends OperationProcessor<DeleteRequest, DeleteResponse> {
}
