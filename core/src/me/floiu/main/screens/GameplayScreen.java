package me.floiu.main.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import me.floiu.main.Main;

public class GameplayScreen extends AbstractScreen {

    public GameplayScreen(Main game) {
        super(game);
        initBoard();
    }

    // Very stupid code
    private void initBoard() {
        Button btn1 = new Button(new ButtonStyle());
        Button btn2 = new Button(new ButtonStyle());
        Button btn3 = new Button(new ButtonStyle());
        Button btn4 = new Button(new ButtonStyle());
        Button btn5 = new Button(new ButtonStyle());
        Button btn6 = new Button(new ButtonStyle());
        Button btn7 = new Button(new ButtonStyle());
        Button btn8 = new Button(new ButtonStyle());
        Button btn9 = new Button(new ButtonStyle());

        btn1.setWidth(100);
        btn1.setHeight(100);
        btn2.setWidth(100);
        btn2.setHeight(100);
        btn3.setWidth(100);
        btn3.setHeight(100);
        btn4.setWidth(100);
        btn4.setHeight(100);
        btn5.setWidth(100);
        btn5.setHeight(100);
        btn6.setWidth(100);
        btn6.setHeight(100);
        btn7.setWidth(100);
        btn7.setHeight(100);
        btn8.setWidth(100);
        btn8.setHeight(100);
        btn9.setWidth(100);
        btn9.setHeight(100);

        btn1.setX(200);
        btn1.setY(250);

        btn2.setX(310);
        btn2.setY(250);

        btn3.setX(420);
        btn3.setY(250);

        btn4.setX(200);
        btn4.setY(360);

        btn5.setX(310);
        btn5.setY(360);

        btn6.setX(420);
        btn6.setY(360);

        btn7.setX(200);
        btn7.setY(470);

        btn8.setX(310);
        btn8.setY(470);

        btn9.setX(420);
        btn9.setY(470);

        btn1.setDebug(true);
        btn2.setDebug(true);
        btn3.setDebug(true);
        btn4.setDebug(true);
        btn5.setDebug(true);
        btn6.setDebug(true);
        btn7.setDebug(true);
        btn8.setDebug(true);
        btn9.setDebug(true);

        stage.addActor(btn1);
        stage.addActor(btn2);
        stage.addActor(btn3);
        stage.addActor(btn4);
        stage.addActor(btn5);
        stage.addActor(btn6);
        stage.addActor(btn7);
        stage.addActor(btn8);
        stage.addActor(btn9);

        btn1.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Pole 1 nacisniete");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btn2.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Pole 2 nacisniete");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btn3.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Pole 3 nacisniete");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btn4.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Pole 4 nacisniete");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btn5.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Pole 5 nacisniete");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btn6.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Pole 6 nacisniete");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btn7.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Pole 7 nacisniete");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btn8.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Pole 8 nacisniete");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        btn9.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Pole 9 nacisniete");
                return super.touchDown(event, x, y, pointer, button);
            }
        });
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
    }
}
