package com.agonzalez.hexchess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen{
    //Screeen
    private Camera camera;
    private Viewport viewport;

    //Graphics
    private SpriteBatch batch;
    private TextureAtlas textureAtlas;

    public Board board;
    public GameState gameState;

    GameScreen(){
        //Recoge las dimensiones del dispositivo
        //Falta que sea responsive a cuando se cambia el tamaño de la pantalla una vez iniciado
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        viewport = new StretchViewport(width, height, camera);
        batch = new SpriteBatch();

        //Coge las texturas para las piezas
        textureAtlas = new TextureAtlas("images/HexaChess.atlas");

        //Recoge la dimension más pequeña para la orientacion del tablero
        int size = (int) Math.min(height, width);

        //Crea un nuevo Tablero y GameState para usar en el render
        board = new Board(size, textureAtlas);
        gameState = new GameState(size, board);

        Gdx.input.setInputProcessor(gameState);
    }

    @Override
    public void render(float delta) {
        //Limpia los colores del fondo
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Inicia el batch que se encarga de las coordenadas
        batch.begin();

        //Crea dos "tableros" el que es visible y el que se encarga del movimiento de las piezas
        board.Draw(batch);
        gameState.Draw(batch);

        //Termina el batch
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    //Metodos autogenerados sin uso por el momento
    @Override
    public void show() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        batch.dispose();
    }
}
