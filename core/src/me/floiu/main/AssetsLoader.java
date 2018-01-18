package me.floiu.main;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class AssetsLoader implements Disposable{

    public final AssetManager manager = new AssetManager();

    public void load() {
        manager.load("ttt_logo.png", Texture.class);
        manager.load("x.png", Texture.class);
        manager.load("o.png", Texture.class);
        manager.load("board.png", Texture.class);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
