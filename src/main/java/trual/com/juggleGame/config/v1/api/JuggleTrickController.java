package trual.com.juggleGame.config.v1.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import trual.com.juggleGame.service.JuggleTricksService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tricks", produces = MediaType.APPLICATION_JSON_VALUE)
public class JuggleTrickController {

    @Autowired
    JuggleTricksService juggleTricksService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection> getThreeBallTricks() throws IOException, URISyntaxException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(juggleTricksService.getThreeBallTricks());
    }
}
