package w7d5.mauriziocrispino.DTO;

import lombok.Getter;

import java.util.Date;

@Getter
public class EventDTO {
    private String title;
    private String description;
    private Date date;
    private String location;
    private int availableSeats;

    public EventDTO(String title, String description, Date date, String location, int availableSeats) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.availableSeats = availableSeats;
    }

    public EventDTO() {
    }
}
