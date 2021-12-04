package trual.com.juggleGame.service;

import org.springframework.stereotype.Component;
import trual.com.juggleGame.dto.model.TrickDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class JuggleTricksService {


    public List<TrickDto> getThreeBallTricks() {
            TrickDto trickDto = new TrickDto();

            trickDto.setBalls(3);
            trickDto.setId(11);
            trickDto.setAnimation("");
            trickDto.setName("Java Trick");

            List<TrickDto> trickDtoList = new ArrayList<>();
            trickDtoList.add(trickDto);
        return trickDtoList;
    }
}
