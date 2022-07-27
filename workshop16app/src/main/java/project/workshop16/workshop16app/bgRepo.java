package project.workshop16.workshop16app;

import jakarta.json.JsonObject;
import project.workshop16.workshop16app.model.Checkers;

public interface bgRepo {
    public void save(Checkers checkers);

    public String findBoardGame(String boardGame);

    public int update(final Checkers checkerObj);

}