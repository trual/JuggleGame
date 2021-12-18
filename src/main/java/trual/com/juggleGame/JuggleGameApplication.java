package trual.com.juggleGame;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import trual.com.juggleGame.db.EmProvider;
import trual.com.juggleGame.service.UserService;

@SpringBootApplication
public class JuggleGameApplication {

	public static void main(String[] args) {
		EmProvider emProvider = EmProvider.getInstance();

		//SpringApplication.run(JuggleGameApplication.class, args);

		UserService userService = new UserService();

		userService.addUser(1, "Alex", "Trujillo", "email@gmail.com", "7348675309");
		userService.getUser(1);

		emProvider.closeEmProvider();
	}

}
