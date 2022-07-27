package project.workshop16.workshop16app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import project.workshop16.workshop16app.model.Checkers;

@Service
public class redisService implements bgRepo {
    
    public int count = 2; 

    @Autowired
    RedisTemplate<String, Checkers> redisTemplate;

    @Override
    public void save(Checkers checkers) {
        redisTemplate.opsForValue().set(count + "", checkers);
    }

    @Override
    public String findBoardGame(String boardGame) {
        //String game = (String)redisTemplate.opsForValue().get(boardGame);
        String game = "test";
        System.out.println(game);
        return game;
    }

    @Override
    public int update(final Checkers checkerObj) {
        if (checkerObj.isUpsert())
            redisTemplate.opsForValue().setIfAbsent(checkerObj.getId(), checkerObj);
        else
            redisTemplate.opsForValue().setIfPresent(checkerObj.getId(), checkerObj);
        Checkers  result = (Checkers) redisTemplate.opsForValue().get(checkerObj.getId());
        if (result != null)
            return 1;
        return 0;
    }

}