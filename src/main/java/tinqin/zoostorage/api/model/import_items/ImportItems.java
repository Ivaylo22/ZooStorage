package tinqin.zoostorage.api.model.import_items;

import org.springframework.stereotype.Service;
import tinqin.zoostorage.api.model.import_items.input.ImportRequest;
import tinqin.zoostorage.api.model.import_items.output.ImportResponse;
import tinqin.zoostorage.api.operations.OperationProcessor;

@Service
public interface ImportItems extends OperationProcessor<ImportRequest, ImportResponse> {
}
