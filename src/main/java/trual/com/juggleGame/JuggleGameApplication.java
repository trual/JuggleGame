package trual.com.juggleGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import trual.com.juggleGame.db.EmProvider;
import trual.com.juggleGame.dto.TrickDto;
import trual.com.juggleGame.service.JuggleTricksService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class JuggleGameApplication {



	public static void main(String[] args) throws IOException {
		EmProvider emProvider = EmProvider.getInstance();

		SpringApplication.run(JuggleGameApplication.class, args);


//add user
//		UserService userService = new UserService();
//		userService.addUser(1, "Alex", "Trujillo", "email@gmail.com", "7348675309");
//		userService.getUser(1);

		JuggleTricksService juggleTricksService = new JuggleTricksService();

		List<TrickDto> trickDtoList = null ;


// populate database
//		trickDtoList = juggleTricksService.getTricksFromJson();
//
//		for (TrickDto trickDto : trickDtoList) {
//
//			juggleTricksService.addTrick(trickDto.getId(),trickDto.getBalls(), trickDto.getName(), trickDto.getAnimation(), trickDto.getPrereq());
//		}


		juggleTricksService.getTrick(1);

		trickDtoList = juggleTricksService.getAllTricks();

		for (TrickDto trickDto : trickDtoList) {

			System.out.println(trickDto);
		}

		//emProvider.closeEmProvider();
	}

}
