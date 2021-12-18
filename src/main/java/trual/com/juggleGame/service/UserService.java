package trual.com.juggleGame.service;

import org.springframework.stereotype.Component;
import trual.com.juggleGame.db.EmProvider;
import trual.com.juggleGame.dto.UserDto;
import trual.com.juggleGame.model.Customer;

import java.util.List;

@Component
public class UserService {

    public List<UserDto> getUsers(){
        return List.of(new UserDto());
    }

    public void getUser(int id){
        EmProvider emf = EmProvider.getInstance();
        Customer customer = Customer.getCustomer(id, emf);
        if (customer != null){
            System.out.println(customer);
        }
    }
}
