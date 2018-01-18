package me.floiu.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.floiu.main.screens.GameplayScreen;

public class GameController {

    private GameplayScreen screen;
    protected SpriteBatch spriteBatch;

    private Texture oImage;
    private Texture xImage;

    public GameController(GameplayScreen screen, SpriteBatch spriteBatch) {
        this.screen = screen;
        this.spriteBatch = spriteBatch;
        init();
    }

    private void init() {
        oImage = new Texture("o.png");
        xImage = new Texture("x.png");
    }

    public void makeMove(int i) {

    }
}
