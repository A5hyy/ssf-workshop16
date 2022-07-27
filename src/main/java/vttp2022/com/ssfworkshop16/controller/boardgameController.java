package vttp2022.com.ssfworkshop16.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.com.ssfworkshop16.model.boardgame;
import vttp2022.com.ssfworkshop16.service.BoardgameRedis;

@RestController
@RequestMapping(path="/api/boardgame", consumes="application/json", produces="application/json")

public class boardgameController {
    private static final Logger logger = LoggerFactory.getLogger(boardgameController.class);

    @Autowired
    BoardgameRedis service;


    @PostMapping
    public ResponseEntity <boardgame> createBoardgame(@RequestBody boardgame bg) {
        logger.info(" " + bg.getName());
        int x = service.save(bg);
        if (x > 0)
            bg.setInsertCount(x);
        return ResponseEntity.ok(bg);
        //JsonObject body;
        //JsonObject body2;
        //try (InputStream is = new ByteArrayInputStream (payload.getBytes())){
        //JsonReader reader = Json.createReader(is);
        //body = reader.readObject();
        //System.out.println(body);
        //URI location = new URI("http://localhost:8080/api/boardgame");
        //service.save(body);
        //body2 = Json.createObjectBuilder().add("insert_count",1).add("id","boardgame").build();
        //return ResponseEntity.created(location).body(body2.toString());
        //} catch(Exception ex) {
        //body = Json.createObjectBuilder().add("error",ex.getMessage ()).build();
        //return ResponseEntity.internalServerError().body(body.toString());
        //}    
    }

    @GetMapping(path="/{bgId}")
    public ResponseEntity <boardgame> getBoardgamebyId(@PathVariable(value="bgId") String bgId){
        boardgame b = service.findById(bgId);
        return ResponseEntity.ok(b);
    }

    @PutMapping(path = "/{bgId}")
    public ResponseEntity<boardgame> updateGameBoard(@RequestBody boardgame bg, @PathVariable(value="bgId") String bgId) {
        int mResult = service.update(bg,bgId);
        if (mResult > 0)
            bg.setUpdateCount(mResult);
        return ResponseEntity.ok(bg);
    }

}
