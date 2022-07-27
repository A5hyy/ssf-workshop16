package project.workshop16.workshop16app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import project.workshop16.workshop16app.model.Checkers;

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
    public ResponseEntity<Checkers> postUser(@RequestBody Checkers checkers) {
    //JsonObject body;
    URI location;

    try {
        //InputStream is = new ByteArrayInputStream(payload.getBytes())
        //JsonReader reader = Json.createReader(is);
        //body = reader.readObject();
        //System.out.println("INSIDE POST-MAPPING METHOD - BODY: " + body);
        location = new URI("http://localhost:8080/api/boardgame");
        int result = service.save(checkers); //If doesn't exist, 1. Else, 0;
        if(result > 0) {
            checkers.setInsertCount(result);
        }
        JsonObject response = Json.createObjectBuilder()
                                    .add("firstName", "GX")
                                    .add("lastName", "Legend")
                                    .add("age", "35")
                                    .add("address", "25 Bleecker St")
                                    .build();

        return ResponseEntity.ok().body(checkers);
    } catch (Exception ex) {
        //body = Json.createObjectBuilder().add("error", ex.getMessage()).build();
        return ResponseEntity.internalServerError().build();
    }
    }

    @GetMapping(path="/boardgame/{boardGameId}")
    public ResponseEntity<Checkers> getBoardGame(@PathVariable String boardGameId) {
        Checkers game = service.findBoardGame(boardGameId);
        JsonObject body;
        try {
            //InputStream is = new ByteArrayInputStream(game.getBytes());
            //JsonReader reader = Json.createReader(is);
            //body = reader.readObject();
            return ResponseEntity.ok().body(game);
        } catch (Exception ex) {
            body = Json.createObjectBuilder().add("error", ex.getMessage()).build();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(path="/boardgame/{boardGameId}")
    public ResponseEntity<Checkers> updateBoardGame(@RequestBody Checkers checkerObj,
                                                    @PathVariable String boardGameId) {
        System.out.println("BEFORE UPDATE: " + checkerObj.getId());
        int mResult = service.update(checkerObj, boardGameId);
        return ResponseEntity.ok(checkerObj);
    }

}
