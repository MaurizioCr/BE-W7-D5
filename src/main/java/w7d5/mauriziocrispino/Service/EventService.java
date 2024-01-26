package w7d5.mauriziocrispino.Service;

import w7d5.mauriziocrispino.Repositories.EventRepository;
import w7d5.mauriziocrispino.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;


}
