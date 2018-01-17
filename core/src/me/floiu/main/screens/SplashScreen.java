package me.floiu.main.screens;

import com.badlogic.gdx.graphics.Texture;

import me.floiu.main.Main;

public class SplashScreen extends AbstractScreen {

    private Texture splashImg;

    public SplashScreen(Main game) {
        super(game);
        init();
    }

    private void init() {
        splashImg = new Texture("ttt_logo.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg, Main.WIDTH/2 - splashImg.getWidth()/2, Main.HEIGHT/2 - splashImg.getHeight()/2);
        spriteBatch.end();
    }
}
