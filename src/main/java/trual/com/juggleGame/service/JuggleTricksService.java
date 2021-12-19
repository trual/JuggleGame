package trual.com.juggleGame.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import trual.com.juggleGame.db.EmProvider;
import trual.com.juggleGame.dto.TrickDto;
import trual.com.juggleGame.model.Trick;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class JuggleTricksService {


    public List<TrickDto> getTricksFromJson() throws IOException {

            ObjectMapper objectMapper = new ObjectMapper();

            List<TrickDto> jsonTricks = objectMapper.readValue(Paths.get("src/main/resources/AllTricks.json").toFile(), new TypeReference<List<TrickDto>>() {});


        return jsonTricks;
    }

    public void addTrick(int id, int balls, String name, String animation, Set<Integer> prereq ) {
        EmProvider emf = EmProvider.getInstance();
        Trick.addTrick(emf, id, balls, name, animation, prereq);
    }

    public void getTrick(int id) {
    EmProvider emf = EmProvider.getInstance();
    Trick trick = Trick.getTrick(emf, id);
        if (trick != null) {
            System.out.println(trick);
        }
    }

    public List<TrickDto> getAllTricks() throws IOException {
        EmProvider emf = EmProvider.getInstance();
        List<Trick> trickList = Trick.getAllTricks(emf);
        List<TrickDto> trickDtoList = new ArrayList<>();
        if (trickList != null) {
            for (Trick trick : trickList) {
                System.out.println(trick);
                trickDtoList.add(new TrickDto(trick));
            }
        }
        else {
            trickDtoList = getTricksFromJson(); //this is here for if no database is running
        }
        return trickDtoList;

    }
}
