package com.agonzalez.hexchess;

import com.badlogic.gdx.Game;

public class HexChess extends Game {
	public MainScreen mainScreen;

	@Override
	public void create() {
		//Pantalla de inicio
		mainScreen = new MainScreen();
		setScreen(mainScreen);
	}

	@Override
	public void dispose() {mainScreen.dispose();}

	@Override
	public void render() {super.render();}

	@Override
	public void resize(int width, int height) {mainScreen.resize(width, height);}

	//Sin uso actualmente
	@Override
	public void pause() {}
	@Override
	public void resume() {}
}
