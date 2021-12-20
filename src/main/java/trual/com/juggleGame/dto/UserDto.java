package trual.com.juggleGame.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String firstName;
    private String LastName;
    private String email;
    private String mobileNumber;
    //private List<Roles> roles;
}
