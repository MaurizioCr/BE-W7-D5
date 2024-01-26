package Service;

import Entities.Event;
import Entities.Role;
import Entities.User;
import Repositories.EventRepository;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;


}
