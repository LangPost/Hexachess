package com.agonzalez.hexchess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainScreen implements Screen {
    private Window pause;
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    public AppPreferences Preferences = new AppPreferences();

    public MainScreen(){
        atlas = new TextureAtlas("images/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("images/uiskin.json"), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(250, 250, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        Image Icono = new Image(new Texture(Gdx.files.internal("images/Icon.png")));

        //ClickListeners
        TextButton playButton = new TextButton("Play", skin, "blue");
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });

        TextButton tutorialButton = new TextButton("Tutorial", skin, "blue");
        tutorialButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new TutorialScreen());
            }
        });

        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new OptionScreen(Preferences));
            }
        });

        TextButton aboutButton = new TextButton("About", skin, "toggle");
        aboutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pause.setVisible(true);
            }
        });

        TextButton exitButton = new TextButton("Exit", skin);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        //Layout
        mainTable.add(Icono).height(70).width(70);
        mainTable.row();
        mainTable.add(playButton);
        mainTable.row();
        mainTable.add(tutorialButton);
        mainTable.row();
        mainTable.add(optionsButton);
        mainTable.row();
        mainTable.add(aboutButton);
        mainTable.row();
        mainTable.add(exitButton);
        stage.addActor(mainTable);


        //About pop-up
        TextButton closePopUp = new TextButton("Close", skin);
        closePopUp.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pause.setVisible(false);
            }
        });

        pause = new Window("About", skin);
        pause.row();
        pause.add("V 0.1");
        pause.row();
        pause.add(closePopUp);
        pause.row();
        pause.pack();
        pause.setMovable(false);
        pause.setVisible(false);
        pause.setBounds(camera.viewportWidth / 4, camera.viewportWidth / 4, 100 , 100 );
        stage.addActor(pause);
        //End about-pop
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        skin.dispose();
        atlas.dispose();
    }
}
