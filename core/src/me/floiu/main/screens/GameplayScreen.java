package me.floiu.main.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.floiu.main.AssetsLoader;
import me.floiu.main.GameController;
import me.floiu.main.Main;

public class GameplayScreen extends AbstractScreen {

    private GameController gc;
    private Texture boardImage;
    private Texture oImage;
    private Texture xImage;
    private AssetsLoader assetsLoader;

    private TextButton resetButton;
    private TextButtonStyle resetButtonStyle;

    private static int BOARD_SIZE = 9;
    private static int BOARD_BUTTON_SIZE = 100;
    private static int BOARD_IMAGE_X = 200;
    private static int BOARD_IMAGE_Y = 250;
    private int[] boardButtonX = {200, 310, 420, 200, 310, 420, 200, 310, 420};
    private int[] boardButtonY = {250, 250, 250, 360, 360, 360, 470, 470, 470};

    public GameplayScreen(Main game, AssetsLoader assetsLoader) {
        super(game);
        this.assetsLoader = assetsLoader;
        gc = new GameController(this, spriteBatch, assetsLoader);
        initAssets();
        initBoard();
        initResetButton();
    }

    private void initAssets() {
        boardImage = assetsLoader.manager.get("board.png", Texture.class);
        oImage = assetsLoader.manager.get("o.png", Texture.class);
        xImage = assetsLoader.manager.get("x.png", Texture.class);
    }

    private void initResetButton() {
        resetButtonStyle = new TextButtonStyle();
        resetButtonStyle.font = new BitmapFont();
        resetButton = new TextButton("Reset", resetButtonStyle);
        resetButton.setWidth(BOARD_BUTTON_SIZE);
        resetButton.setHeight(BOARD_BUTTON_SIZE);
        resetButton.setX(10);
        resetButton.setY(590);
        resetButton.setDebug(true);

        stage.addActor(resetButton);

        resetButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    gc.resetGame();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    // Init playing board
    private void initBoard() {
        for (int i=1; i<=BOARD_SIZE; i++) {
            final Button _tempbutton = new Button(new ButtonStyle());
            _tempbutton.setWidth(BOARD_BUTTON_SIZE);
            _tempbutton.setHeight(BOARD_BUTTON_SIZE);
            _tempbutton.setX(boardButtonX[i-1]);
            _tempbutton.setY(boardButtonY[i-1]);

            stage.addActor(_tempbutton);

            final int fieldID = i;

            _tempbutton.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (button == Input.Buttons.LEFT) {
                        gc.makeMove(fieldID, event);
                    }
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
        }
    }

    private void whoIsNow() {
        spriteBatch.begin();
        if (gc.getWhoIsNow() == 'x') {
            spriteBatch.draw(xImage, 590, 590);
        } else {
            spriteBatch.draw(oImage, 590, 590);
        }
        spriteBatch.end();
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
        for (int i=1; i<=BOARD_SIZE; i++) {
            char [] _tempBoardStatus = gc.getBoardStatus();
            if (_tempBoardStatus[i-1] == 'x') {
                spriteBatch.draw(xImage, boardButtonX[i-1], boardButtonY[i-1]);
            } else  if (_tempBoardStatus[i-1] == 'o') {
                spriteBatch.draw(oImage, boardButtonX[i-1], boardButtonY[i-1]);
            } else {

            }
        }
        spriteBatch.end();
        whoIsNow();
    }
}
