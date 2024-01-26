package w7d5.mauriziocrispino.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import w7d5.mauriziocrispino.DTO.EventDTO;
import w7d5.mauriziocrispino.Entities.Event;
import w7d5.mauriziocrispino.Entities.User;
import w7d5.mauriziocrispino.Service.EventService;

import java.util.List;
import java.util.UUID;
    @RestController
    @RequestMapping("/events")
    public class EventController {

        @Autowired
        private EventService eventService;
//if (!existingEvent.getOrganizer().equals(organizer)) {
//            throw new UnauthorizedException("Non sei autorizzato a modificare questo evento.");
//        }
        @PostMapping
        @PreAuthorize("hasAuthority('ORGANIZZATORE')")
        public ResponseEntity<Event> createEvent(@RequestBody EventDTO eventDTO,
                                                 @AuthenticationPrincipal User organizer) {
            Event createdEvent = eventService.createEvent(eventDTO, organizer);
            return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<Event>> getAllEvents() {
            List<Event> events = eventService.getAllEvents();
            return new ResponseEntity<>(events, HttpStatus.OK);
        }

        @GetMapping("/{eventId}")
        public ResponseEntity<Event> getEventById(@PathVariable UUID eventId) {
            Event event = eventService.getEventById(eventId);
            return new ResponseEntity<>(event, HttpStatus.OK);
        }

        @PutMapping("/{eventId}")
        @PreAuthorize("hasAuthority('ORGANIZZATORE')")
        public ResponseEntity<Event> updateEvent(@PathVariable UUID eventId,
                                                 @RequestBody EventDTO eventDTO,
                                                 @AuthenticationPrincipal User organizer) {
            Event updatedEvent = eventService.updateEvent(eventId, eventDTO, organizer);
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        }

        @DeleteMapping("/{eventId}")
        @PreAuthorize("hasAuthority('ORGANIZZATORE')")
        public ResponseEntity<Void> deleteEvent(@PathVariable UUID eventId,
                                                @AuthenticationPrincipal User organizer) {
            eventService.deleteEvent(eventId, organizer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
