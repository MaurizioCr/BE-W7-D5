package w7d5.mauriziocrispino.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import w7d5.mauriziocrispino.DTO.NewUserDTO;
import w7d5.mauriziocrispino.DTO.NewUserResponseDTO;
import w7d5.mauriziocrispino.DTO.UserLoginDTO;
import w7d5.mauriziocrispino.DTO.UserLoginResponseDTO;
import w7d5.mauriziocrispino.Entities.User;
import w7d5.mauriziocrispino.Exceptions.BadRequestException;
import w7d5.mauriziocrispino.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation) {

        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            User newUser = authService.save(newUserPayload);

            return new NewUserResponseDTO(newUser.getId());
        }
    }
}
