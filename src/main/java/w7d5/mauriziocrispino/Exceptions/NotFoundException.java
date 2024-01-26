package w7d5.mauriziocrispino.Exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(UUID id) {
        super("Elemento con id " + id + " non trovato!");
    }
}
