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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainScreen implements Screen {
<<<<<<< Updated upstream

=======
    private Window aboutScreen;
>>>>>>> Stashed changes
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private Texture img;

    public MainScreen(){
        atlas = new TextureAtlas("images/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("images/uiskin.json"), atlas);
        img = new Texture("images/Icon.png");

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

        Image prueba = new Image(new Texture(Gdx.files.internal("images/Icon.png")));

        TextButton playButton = new TextButton("Play", skin, "blue");
        TextButton tutorialButton = new TextButton("Tutorial", skin, "blue");
        TextButton optionsButton = new TextButton("Options", skin);
        TextButton aboutButton = new TextButton("About", skin, "toggle");
        TextButton exitButton = new TextButton("Exit", skin);

        //ClickListeners
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });
        tutorialButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
<<<<<<< Updated upstream
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
=======
                //
>>>>>>> Stashed changes
            }
        });
        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Añadir codigo
            }
        });
        aboutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
<<<<<<< Updated upstream
                //Añadir codigo
=======
                aboutScreen.setVisible(true);
>>>>>>> Stashed changes
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Layout
        mainTable.add(prueba).height(70).width(70);
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

<<<<<<< Updated upstream
        //Add table to stage
        stage.addActor(mainTable);
=======
        //About pop-up
        TextButton closePopUp = new TextButton("Close", skin);
        closePopUp.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                aboutScreen.setVisible(false);
            }
        });

        aboutScreen = new Window("About", skin);
        aboutScreen.row();
        aboutScreen.add("V 0.1");
        aboutScreen.row();
        aboutScreen.add(closePopUp);
        aboutScreen.row();
        aboutScreen.pack();
        aboutScreen.setMovable(false);
        aboutScreen.setVisible(false);
        aboutScreen.setBounds(camera.viewportWidth / 4, camera.viewportWidth / 4, 100 , 100 );
        stage.addActor(aboutScreen);
        //End about-pop
>>>>>>> Stashed changes
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
