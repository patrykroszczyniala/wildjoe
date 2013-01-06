package pl.pepuch.wildjoe.model;

import pl.pepuch.wildjoe.core.WildJoe;
import playn.core.Json.Array;
import playn.core.PlayN;
import playn.core.util.Callback;

public class ScoresModel extends StaticModel {

    private Array jsonArray;

    public void updateScores(Callback<String> callback) {
        PlayN.net().get(WildJoe.address + "?action=scores", callback);
    }

    public Array jsonArray() {
        return jsonArray;
    }

    public void setJsonArray(Array jsonArray) {
        this.jsonArray = jsonArray;
    }
}
