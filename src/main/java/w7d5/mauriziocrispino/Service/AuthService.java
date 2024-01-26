package w7d5.mauriziocrispino.Service;

import org.springframework.stereotype.Service;
import w7d5.mauriziocrispino.DTO.NewUserDTO;
import w7d5.mauriziocrispino.DTO.UserLoginDTO;
import w7d5.mauriziocrispino.Entities.Role;
import w7d5.mauriziocrispino.Entities.User;
import w7d5.mauriziocrispino.Exceptions.BadRequestException;
import w7d5.mauriziocrispino.Exceptions.UnauthorizedException;
import w7d5.mauriziocrispino.Repositories.UserRepository;
import w7d5.mauriziocrispino.Security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {
    @Autowired
    private UserDetailsService usersDetailService;

    @Autowired
    private UserService usersService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {
        User user = usersService.findByEmail(body.email());


        if (bcrypt.matches(body.password(), user.getPassword())) {

            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public User save(NewUserDTO body) {
        userRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        // newUser.setPassword(body.password());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.USER);
        return userRepository.save(newUser);
    }
}

