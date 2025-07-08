package backend.car_rental.controlers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.dto.jwt.AuthRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    private final 
    @PostMapping("/login")
    public String login(AuthRequest authRequest){
        return "";
    }

}