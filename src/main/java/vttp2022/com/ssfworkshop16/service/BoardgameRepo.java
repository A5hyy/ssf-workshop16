package vttp2022.com.ssfworkshop16.service;


import vttp2022.com.ssfworkshop16.model.boardgame;


public interface BoardgameRepo {

    public int save(final boardgame bg);
    public boardgame findById(final String bgId);
    public int update(final boardgame bg,String bgId);
    
}
