package tinqin.zoostorage.operations;

public interface OperationProcessor<I extends OperationInput, R extends OperationResult> {
    R process(I input) throws Exception;
}
