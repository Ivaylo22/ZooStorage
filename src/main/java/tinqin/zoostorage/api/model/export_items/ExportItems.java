package tinqin.zoostorage.api.model.export_items;

import org.springframework.stereotype.Service;
import tinqin.zoostorage.api.operations.OperationProcessor;

@Service
public interface ExportItems extends OperationProcessor<ExportRequest, ExportResponse> {
}
