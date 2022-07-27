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
    public int save(Checkers checkers) {
        redisTemplate.opsForValue().set(checkers.getId() + "", checkers);
        Checkers result = (Checkers) redisTemplate.opsForValue().get(checkers.getId());
        if(result != null) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public Checkers findBoardGame(String boardGame) {
        Checkers game = redisTemplate.opsForValue().get(boardGame);
        //String game = "test";
        System.out.println(game);
        return game;
    }

    @Override
    public int update(final Checkers checkerObj, String Id) {

        Checkers  result = (Checkers) redisTemplate.opsForValue().get(Id);

        System.out.println("INUPDATEMETHOD: " + result.getId());
        if (result.isUpsert())
            redisTemplate.opsForValue().setIfAbsent(Id, checkerObj);
        else
            redisTemplate.opsForValue().setIfPresent(Id, checkerObj);
        if (result != null) {
            checkerObj.setUpdateCount(result.getUpdateCount() + 1); //Incrementing Update Count
            checkerObj.setId(result.getId());
            redisTemplate.opsForValue().setIfPresent(Id, checkerObj);
            return checkerObj.getUpdateCount();
        }
        return 0;
    }

}