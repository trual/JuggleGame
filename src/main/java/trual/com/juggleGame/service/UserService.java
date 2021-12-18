package trual.com.juggleGame.service;

import org.springframework.stereotype.Component;
import trual.com.juggleGame.db.EmProvider;
import trual.com.juggleGame.dto.UserDto;
import trual.com.juggleGame.model.User;

import java.util.List;

@Component
public class UserService {

    public List<UserDto> getUsers(){
        return List.of(new UserDto());
    }

    public void addUser(int id, String firstName, String lastName, String email, String phone){
        EmProvider emf = EmProvider.getInstance();
        User.addUser(emf, id, firstName, lastName, email, phone);
    }


    public void getUser(int id){
        EmProvider emf = EmProvider.getInstance();
        User user = User.getUser(emf, id);
        if (user != null){
            System.out.println(user);
        }
    }
}
