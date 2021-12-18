package trual.com.juggleGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import trual.com.juggleGame.db.EmProvider;
import trual.com.juggleGame.model.Customer;
import trual.com.juggleGame.service.UserService;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class JuggleGameApplication {
//	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
//			.createEntityManagerFactory("juggleGame");


	public static void main(String[] args) {
		EmProvider emProvider = EmProvider.getInstance();

		//SpringApplication.run(JuggleGameApplication.class, args);

		UserService userService = new UserService();

		userService.getUser(1);

		emProvider.closeEmProvider();
	}

}
