package project.workshop16.workshop16app;

import jakarta.json.JsonObject;
import project.workshop16.workshop16app.model.Checkers;

public interface bgRepo {
    public int save(Checkers checkers);

    public Checkers findBoardGame(String boardGame);

    public int update(final Checkers checkerObj, String Id);

}