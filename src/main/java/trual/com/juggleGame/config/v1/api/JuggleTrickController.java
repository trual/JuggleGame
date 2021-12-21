package trual.com.juggleGame.config.v1.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trual.com.juggleGame.dto.TrickDto;
import trual.com.juggleGame.service.JuggleTricksService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/tricks", produces = MediaType.APPLICATION_JSON_VALUE)
public class JuggleTrickController {

    @Autowired
    JuggleTricksService juggleTricksService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAll")
    public ResponseEntity<Collection> getAllTricks() throws IOException, URISyntaxException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(juggleTricksService.getAllTricks());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addTrick")
    public ResponseEntity<TrickDto> addTrick(@RequestBody TrickDto t){
        juggleTricksService.addTrick(t);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(t);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/removeTrick")
    public ResponseEntity<Boolean> removeTrick(Integer id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(juggleTricksService.removeTrick(id));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateTrick")
    public ResponseEntity<TrickDto> updatePrereq(@RequestBody TrickDto t){
        juggleTricksService.updateTrick(t);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(t);
    }

}
