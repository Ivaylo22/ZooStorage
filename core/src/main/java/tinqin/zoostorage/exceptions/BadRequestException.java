package tinqin.zoostorage.exceptions;

public class BadRequestException extends Exception{
    public BadRequestException() {
        super(String.format("Request failed"));
    }
}
