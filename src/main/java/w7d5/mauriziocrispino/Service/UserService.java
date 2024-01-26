package w7d5.mauriziocrispino.Service;

import w7d5.mauriziocrispino.Entities.User;
import w7d5.mauriziocrispino.Exceptions.NotFoundException;
import w7d5.mauriziocrispino.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository usersRepository;
    public User findById(UUID id) {
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User findByEmail(String email) throws NotFoundException {
        return usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }
}
