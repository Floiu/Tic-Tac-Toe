package me.floiu.main.screens;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.utils.Timer;
import me.floiu.main.AssetsLoader;
import me.floiu.main.Main;

public class SplashScreen extends AbstractScreen {

    private Texture splashImg;
    public AssetsLoader assetsLoader;

    public SplashScreen(final Main game) {
        super(game);
        init();

        if (assetsLoader.manager.update()) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.setScreen(new GameplayScreen(game, assetsLoader));
                }
            }, 2);
        }
    }


    private void init() {
        assetsLoader = new AssetsLoader();
        assetsLoader.load();
        assetsLoader.manager.finishLoading();
        splashImg = assetsLoader.manager.get("ttt_logo.png", Texture.class);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg, Main.WIDTH/2 - splashImg.getWidth()/2, Main.HEIGHT/2 - splashImg.getHeight()/2);
        spriteBatch.end();
    }
}
