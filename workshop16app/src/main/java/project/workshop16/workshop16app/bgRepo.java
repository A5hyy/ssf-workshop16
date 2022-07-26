package project.workshop16.workshop16app;

import jakarta.json.JsonObject;

public interface bgRepo {
    public void save(JsonObject body);

    public void findBoardGame(String boardGame);

}