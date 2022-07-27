package vttp2022.com.ssfworkshop16.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp2022.com.ssfworkshop16.model.boardgame;


@Service
public class BoardgameRedis implements BoardgameRepo{
    private static final Logger logger = LoggerFactory.getLogger(BoardgameRedis.class);


    @Autowired
    @Qualifier("games")
    RedisTemplate<String, boardgame> redisTemplate;

    @Override
    public int save(final boardgame bg){
        redisTemplate.opsForValue().set(bg.getId(), bg);
        boardgame result = (boardgame) redisTemplate.opsForValue().get(bg.getId());
        if (result != null)
            return 1;
        return 0;
    }

    @Override
    public boardgame findById(final String bgId){
        boardgame result = (boardgame) redisTemplate.opsForValue().get(bgId);
        logger.info(">>>"+bgId);
        return result;
    }
    
    @Override
    public int update(final boardgame bg) {
        logger.info("Save mastermind > " + logger);
        if (bg.isUpsert())
            redisTemplate.opsForValue().setIfAbsent(bg.getId(), bg);
        else
            redisTemplate.opsForValue().setIfPresent(bg.getId(), bg);
        boardgame result = (boardgame) redisTemplate.opsForValue().get(bg.getId());
        if (result != null)
            return 1;
        return 0;
    }

    public Set<String> searchKeys(String index) {
        String pattern = "*%s*".formatted(index);
        return redisTemplate.keys(pattern);
    }
}
