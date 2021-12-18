package trual.com.juggleGame.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import trual.com.juggleGame.dto.TrickDto;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class JuggleTricksService {


    public List<TrickDto> getThreeBallTricks() throws IOException, URISyntaxException {
            TrickDto trickDto = new TrickDto();

            trickDto.setBalls(3);
            trickDto.setId(11);
            trickDto.setAnimation("");
            trickDto.setName("Java Trick");

            ObjectMapper objectMapper = new ObjectMapper();

            List<TrickDto> threeBalltricks = objectMapper.readValue(Paths.get("src/main/resources/ThreeBallTricks.json").toFile(), new TypeReference<List<TrickDto>>() {});


            List<TrickDto> trickDtoList = new ArrayList<>();
            trickDtoList.add(trickDto);
        return threeBalltricks;
    }
}
