package com.agonzalez.hexchess;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionScreen implements Screen{
    private Stage stage;
    private AppPreferences Preferences;
    private TextureAtlas atlas;
    protected Skin skin;

    public OptionScreen(AppPreferences preferences){
        Preferences = preferences;
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        atlas = new TextureAtlas("images/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("images/uiskin.json"), atlas);

        final Slider volumeMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeMusicSlider.setValue(Preferences.getMusicVolume());
        volumeMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                Preferences.setMusicVolume(volumeMusicSlider.getValue());
                return false;
            }
        });

        final Slider soundMusicSlider = new Slider(0f, 1f, 0.1f, false, skin);
        soundMusicSlider.setValue(Preferences.getSoundVolume());
        soundMusicSlider.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                Preferences.setSoundVolume(soundMusicSlider.getValue());
                return false;
            }
        });

        final CheckBox musicCheckbox = new CheckBox(null, skin);
        musicCheckbox.setChecked(Preferences.isMusicEnabled());
        musicCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                Preferences.setMusicEnabled(enabled);
                return false;
            }
        });

        final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
        soundEffectsCheckbox.setChecked(Preferences.isSoundEffectsEnabled());
        soundEffectsCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = soundEffectsCheckbox.isChecked();
                Preferences.setSoundEffectsEnabled(enabled);
                return false;
            }
        });

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainScreen());
                //Falta codigo
            }
        });
        TextButton acceptButton = new TextButton("Accept", skin);
        acceptButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainScreen());
                //Falta codigo
            }
        });

        final CheckBox multiplaterCheckbox = new CheckBox(null, skin);
        multiplaterCheckbox.setChecked(Preferences.isMultiplayerEnabled());
        multiplaterCheckbox.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = multiplaterCheckbox.isChecked();
                Preferences.setMultiplayerEnabled(enabled);
                return false;
            }
        });


        Label titleLabel = new Label( "Preferences", skin );
        Label volumeMusicLabel = new Label( "Music Volume", skin );
        Label volumeSoundLabel = new Label( "Sound Volume", skin );
        Label musicOnOffLabel = new Label( "Music", skin );
        Label soundOnOffLabel = new Label( "Sound Effect", skin );
        Label multiplayerOnOffLabel = new Label( "Multiplayer", skin );

        table.add(titleLabel).colspan(2);
        table.row();
        table.add(volumeMusicLabel);
        table.add(volumeMusicSlider);
        table.row();
        table.add(musicOnOffLabel);
        table.add(musicCheckbox);
        table.row();
        table.add(volumeSoundLabel);;
        table.add(soundMusicSlider);
        table.row();
        table.add(soundOnOffLabel);
        table.add(soundEffectsCheckbox);
        table.row();
        table.add(multiplayerOnOffLabel);
        table.add(multiplaterCheckbox);
        table.row().padRight(1);
        table.add(backButton).width(Gdx.graphics.getWidth()/4).center();
        table.add(acceptButton).width(Gdx.graphics.getWidth()/4).center();
        table.row();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) { stage.getViewport().update(width, height, true); }

    //En des-uso
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}

}