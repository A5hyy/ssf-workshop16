package project.workshop16.workshop16app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.http.ResponseEntity;
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
    @PostMapping(path="/boardgame", consumes="application/json")
    public ResponseEntity<String> postUser(@RequestBody String payload) {
    JsonObject body;

    try(InputStream is = new ByteArrayInputStream(payload.getBytes())) {
        JsonReader reader = Json.createReader(is);
        body = reader.readObject();
        System.out.println(body);
    } catch (Exception ex) {
        body = Json.createObjectBuilder().add("error", ex.getMessage()).build();
        return ResponseEntity.internalServerError().body(body.toString());
    }
    return ResponseEntity.internalServerError().body(body.toString());
    }
}
