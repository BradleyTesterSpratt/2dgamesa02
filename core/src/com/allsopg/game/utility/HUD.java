package com.allsopg.game.utility;

import com.allsopg.game.TBWGame;
import com.allsopg.game.bodies.PlayerCharacter;
import com.allsopg.game.screens.EndScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.allsopg.game.utility.Constants.INFINITE_FONT;

public class HUD implements Disposable {

    public Stage stage;
    private Viewport viewport;
    //structural elements for UI
    Table tableData;
    Table tableControls;
    // Navigation widgets
    private Button leftBtn,rightBtn,upBtn,downBtn;
    private PlayerCharacter playerCharacter;
    private TBWGame game;

    //score && time tracking variables
    //private Integer worldTimer;
    private float timeCount;
    private int health;
    private static Integer score;
    private boolean timeUp;
    private int stamina;

    //Scene2D Widgets
    private Label countdownLabel, healthLabel, linkLabel, staminaLabel, staminaCount;
    private static Label scoreLabel;

    public HUD(SpriteBatch sb, PlayerCharacter playerCharacter, TBWGame tbwGame) {
        this.playerCharacter = playerCharacter;
        this.game = tbwGame;
        //define tracking variables
        //worldTimer = Constants.LEVEL_TIME;
        health=playerCharacter.health;
        timeCount = 0;
        score = 0;
        stamina=100;
        //new camera used to setup the HUD viewport seperate from the main Game Camera
        //define stage using that viewport and games spritebatch
        viewport = new FitViewport(Constants.VIRTUAL_WIDTH,
                Constants.VIRTUAL_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        tableData = new Table();
        tableData.top();
        tableData.setFillParent(true);
        tableControls = new Table();
        tableControls.bottom();
        tableControls.setFillParent(true);

        createScoreAndTimer();
        createNavButtons();
        stage.addActor(tableData);
        stage.addActor(tableControls);
        Gdx.input.setInputProcessor(stage);
    }

    private void createScoreAndTimer(){
        countdownLabel = new Label(String.format("%03d", health),
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal(INFINITE_FONT)), Color.RED));
        scoreLabel = new Label(String.format("%03d", score),
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal(INFINITE_FONT)), Color.BLUE));
        healthLabel = new Label("HEALTH",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal(INFINITE_FONT)), Color.RED));
        linkLabel = new Label("POINTS",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal(INFINITE_FONT)), Color.BLUE));
        staminaLabel = new Label("STAMINA",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal(INFINITE_FONT)), Color.BLUE));
        staminaCount = new Label(String.format("%03d", stamina),
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal(INFINITE_FONT)), Color.BLUE));
        //labels added to table using padding and expandX
        tableData.add(linkLabel).padTop(5).padLeft(120);
        tableData.add(scoreLabel).expandX();
        tableData.add(healthLabel).padRight(20);
        tableData.add(countdownLabel).expandX().padTop(5);
        tableData.add(staminaLabel).padRight(120);
        tableData.add(staminaCount).expandX().padTop(5);
    }

    private void createNavButtons(){
        Texture actorUpBtn =
                new Texture(Gdx.files.internal("buttons/up.png"));
        Texture actorLeftBtn =
                new Texture(Gdx.files.internal("buttons/left.png"));
        Texture actorRightBtn =
                new Texture(Gdx.files.internal("buttons/right.png"));

        Button.ButtonStyle buttonStyleUp = new Button.ButtonStyle();
        buttonStyleUp.up =
                new TextureRegionDrawable(new TextureRegion(actorUpBtn));
        upBtn = new Button( buttonStyleUp );

        Button.ButtonStyle buttonStyleLeft = new Button.ButtonStyle();
        buttonStyleLeft.up =
                new TextureRegionDrawable(new TextureRegion(actorLeftBtn));
        leftBtn = new Button( buttonStyleLeft );

        Button.ButtonStyle buttonStyleRight = new Button.ButtonStyle();
        buttonStyleRight.up =
                new TextureRegionDrawable(new TextureRegion(actorRightBtn));
        rightBtn = new Button( buttonStyleRight );

        //add buttons
        tableControls.add(leftBtn).padLeft(25);
        tableControls.add(upBtn).expandX().padLeft(200);
        tableControls.add(rightBtn).expandX().padRight(25);
        //add listeners to the buttons
        addButtonListeners();
    }

    private void addButtonListeners(){
        //up
        upBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerCharacter.move(CurrentDirection.UP);
            }
        });
        //left
        leftBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerCharacter.move(CurrentDirection.LEFT);
            }
        });
        //right
        rightBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerCharacter.move(CurrentDirection.RIGHT);
            }
        });
    }

    public void update(float dt) {
        health = playerCharacter.health;
        if (!playerCharacter.dead) {
            score = playerCharacter.score;
        }
        scoreLabel.setText(String.format("%06d", score));
        timeCount += dt;
        if (timeCount >= 1) {
            if (health> 0) {
                health--;
                playerCharacter.health--;
            } else {
                timeUp = true;
                GameData.getInstance().setScore(score);
                GameData.getInstance().setTime(health);
                playerCharacter.Die();
                //game.setScreen(new EndScreen());

            }
            countdownLabel.setText(String.format("%03d", health));
            timeCount = 0;
        }
        if (Gdx.input.isTouched()& timeUp) {
            game.setScreen(new EndScreen(score));
        }
    }

    public static void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public boolean isTimeUp() {
        return timeUp;
    }


    public static Label getScoreLabel() {
        return scoreLabel;
    }

    public static Integer getScore() {
        return score;
    }


}