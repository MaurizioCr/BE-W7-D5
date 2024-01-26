package Service;

import Entities.Event;
import Entities.Role;
import Entities.User;
import Exceptions.NotFoundException;
import Repositories.EventRepository;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
