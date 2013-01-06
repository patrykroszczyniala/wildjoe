package pl.pepuch.wildjoe.controller;

import pl.pepuch.wildjoe.core.WildJoe;
import pl.pepuch.wildjoe.model.ScoresModel;
import pl.pepuch.wildjoe.view.ScoresView;
import playn.core.PlayN;
import playn.core.util.Callback;
import react.UnitSlot;

public class Scores extends StaticActor {

    public Scores(final WildJoe game) {
        model = new ScoresModel();
        view = new ScoresView(model());
        PlayN.graphics().rootLayer().add(view().layer());
        hide();

        view().back().clicked().connect(new UnitSlot() {
            @Override
            public void onEmit() {
                PlayN.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        hide();
                        game.menu().show();
                    }
                });
            }
        });
    }

    public void show() {
        view().layer().setVisible(true);
        final Loader loader = new Loader();
        loader.start();

        model().updateScores(new Callback<String>() {
            @Override
            public void onSuccess(String result) {
                PlayN.log().debug("scores updated");
                try {
                    model().setJsonArray(PlayN.json().parseArray(result));
                } catch (Exception e) {
                }
                view().updateScores();
                loader.stop();
            }

            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().debug("Unable to get scores from server");
                loader.stop();
            }
        });
    }

    public void hide() {
        view().layer().setVisible(false);
    }

    @Override
    public ScoresView view() {
        return (ScoresView) view;
    }

    @Override
    public ScoresModel model() {
        return (ScoresModel) model;
    }
}
