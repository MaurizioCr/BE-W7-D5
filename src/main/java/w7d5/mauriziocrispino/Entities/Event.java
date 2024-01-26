
package w7d5.mauriziocrispino.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    private Date date;
    private String location;
    private int availableSeats;

    public Event(String title, String description, Date date, String location, int availableSeats, User organizer) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.availableSeats = availableSeats;
        this.organizer = organizer;
    }

    public Event() {
    }

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;
}
