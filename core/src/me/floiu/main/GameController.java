package me.floiu.main;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.floiu.main.screens.GameplayScreen;

public class GameController {

    protected SpriteBatch spriteBatch;
    private GameplayScreen screen;
    private AssetsLoader assetsLoader;

    private Texture oImage;
    private Texture xImage;

    public GameController(GameplayScreen screen, SpriteBatch spriteBatch, AssetsLoader assetsLoader) {
        this.screen = screen;
        this.spriteBatch = spriteBatch;
        this.assetsLoader = assetsLoader;
        init();
    }

    private void init() {
        oImage = assetsLoader.manager.get("o.png", Texture.class);
        xImage = assetsLoader.manager.get("x.png", Texture.class);
    }

    public void makeMove(int i) {

    }
}
