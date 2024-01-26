package w7d5.mauriziocrispino.DTO;

import lombok.Getter;

import java.util.Date;

@Getter
public record EventDTO(String title, String description, Date date, String location, int availableSeats) {
}
