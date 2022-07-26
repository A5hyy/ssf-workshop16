package project.workshop16.workshop16app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;

@Service
public class redisService implements bgRepo {
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(JsonObject body) {
        redisTemplate.opsForValue().set("boardgame", body.toString());
    }

    @Override
    public void findBoardGame(String boardGame) {
        String game = (String)redisTemplate.opsForValue().get(boardGame);
        System.out.println(game);
    }

}
