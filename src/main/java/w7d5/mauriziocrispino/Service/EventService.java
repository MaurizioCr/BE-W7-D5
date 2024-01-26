package w7d5.mauriziocrispino.Service;

import w7d5.mauriziocrispino.DTO.EventDTO;
import w7d5.mauriziocrispino.Entities.Event;
import w7d5.mauriziocrispino.Entities.User;
import w7d5.mauriziocrispino.Exceptions.NotFoundException;
import w7d5.mauriziocrispino.Exceptions.UnauthorizedException;
import w7d5.mauriziocrispino.Repositories.EventRepository;
import w7d5.mauriziocrispino.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventDTO eventDTO, User organizer) {
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.getTitle());
        newEvent.setDescription(eventDTO.getDescription());
        newEvent.setDate(eventDTO.getDate());
        newEvent.setLocation(eventDTO.getLocation());
        newEvent.setAvailableSeats(eventDTO.getAvailableSeats());
        newEvent.setOrganizer(organizer);

        return eventRepository.save(newEvent);
    }
    public Event getEventById(UUID eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento non trovato con ID: " + eventId));
    }
    public Event updateEvent(UUID eventId, EventDTO eventDTO, User organizer) {
        Event existingEvent = getEventById(eventId);

        existingEvent.setTitle(eventDTO.getTitle());
        existingEvent.setDescription(eventDTO.getDescription());
        existingEvent.setDate(eventDTO.getDate());
        existingEvent.setLocation(eventDTO.getLocation());
        existingEvent.setAvailableSeats(eventDTO.getAvailableSeats());

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(UUID eventId, User organizer) {
        Event existingEvent = getEventById(eventId);
        eventRepository.delete(existingEvent);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

}
