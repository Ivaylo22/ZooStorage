package tinqin.zoostorage.exceptions;

public class NotEnoughQuantityException extends Exception{
    public NotEnoughQuantityException(String name) {
        super(String.format("%s does not have enough quantity in the storage", name));
    }
}
