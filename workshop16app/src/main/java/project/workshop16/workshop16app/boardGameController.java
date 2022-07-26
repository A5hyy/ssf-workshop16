package project.workshop16.workshop16app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

/*
PS C:\Users\vans_\sdf-workshop1> 
git add . (add ALL content of cart to github)
git commit -m "While Loop"                  (add comment while committing)
git push origin main                        (push to main branch)
*/

@RestController
@RequestMapping(path="/api")
public class boardGameController {

    @Autowired
    redisService service;

    @PostMapping(path="/boardgame", consumes="application/json")
    public ResponseEntity<String> postUser(@RequestBody String payload) {
    JsonObject body;
    URI location;

    try(InputStream is = new ByteArrayInputStream(payload.getBytes())) {
        JsonReader reader = Json.createReader(is);
        body = reader.readObject();
        System.out.println(body);
        location = new URI("http://localhost:8080/api/boardgame");
        service.save(body);
        JsonObject response = Json.createObjectBuilder()
                                    .add("firstName", "GX")
                                    .add("lastName", "Legend")
                                    .add("age", "35")
                                    .add("address", "25 Bleecker St")
                                    .build();

        return ResponseEntity.created(location).body(response.toString());
    } catch (Exception ex) {
        body = Json.createObjectBuilder().add("error", ex.getMessage()).build();
        return ResponseEntity.internalServerError().body(body.toString());
    }
    }

    @GetMapping(path="/boardgame/{boardGameId}")
    public ResponseEntity<String> getBoardGame(@PathVariable String boardGameId) {
        service.findBoardGame(boardGameId);
        return null;
    }

}
