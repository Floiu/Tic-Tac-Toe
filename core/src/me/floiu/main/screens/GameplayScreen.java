package me.floiu.main.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.floiu.main.AssetsLoader;
import me.floiu.main.GameController;
import me.floiu.main.Main;

public class GameplayScreen extends AbstractScreen {

    private GameController gc;
    private Texture boardImage;
    private AssetsLoader assetsLoader;

    private static int BOARD_SIZE = 9;
    private static int BOARD_BUTTON_SIZE = 100;
    private static int BOARD_IMAGE_X = 200;
    private static int BOARD_IMAGE_Y = 250;
    private int[] boardButtonX = {200, 310, 420, 200, 310, 420, 200, 310, 420};
    private int[] boardButtonY = {250, 250, 250, 360, 360, 360, 470, 470, 470};

    public GameplayScreen(Main game, AssetsLoader assetsLoader) {
        super(game);
        this.assetsLoader = assetsLoader;
        initAssets();
        initBoard();
        initGameController();
    }

    private void initAssets() {
        boardImage = assetsLoader.manager.get("board.png", Texture.class);
    }

    private void initGameController() {
        gc = new GameController(this, spriteBatch);
    }

    // Init playing board
    private void initBoard() {
        for (int i=1; i<=BOARD_SIZE; i++) {
            Button _tempbutton = new Button(new ButtonStyle());
            _tempbutton.setWidth(BOARD_BUTTON_SIZE);
            _tempbutton.setHeight(BOARD_BUTTON_SIZE);
            _tempbutton.setX(boardButtonX[i-1]);
            _tempbutton.setY(boardButtonY[i-1]);

            stage.addActor(_tempbutton);

            final String onClickMessage = "Pole numer " + i + " zostało naciśnięte";

            _tempbutton.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println(onClickMessage);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        stage.act();
        spriteBatch.begin();
        spriteBatch.draw(boardImage, BOARD_IMAGE_X, BOARD_IMAGE_Y); // Draw board image - 4 lines
        spriteBatch.end();
    }
}
