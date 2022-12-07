package telran.java45.accounting.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class UserRegisterDto {
    String login;
    String password;
    String firstName;
    String lastName;
    
}
