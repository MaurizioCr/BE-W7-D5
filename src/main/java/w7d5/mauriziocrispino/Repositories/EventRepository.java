package w7d5.mauriziocrispino.Repositories;

import w7d5.mauriziocrispino.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository <Event, UUID> {
}
