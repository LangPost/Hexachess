package com.agonzalez.hexchess;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Board {
    public TokenInfo tokens[][];

    public int size;
    public int squareSize;

    public Texture boardTexture;
    public TextureRegion boardTextureRegion;

    public TextureAtlas tokenAtlas;

    public IntPoint2D whiteKing;
    public IntPoint2D blackKing;

    public IntPoint2D lastFrom;
    public IntPoint2D lastTo;
    public TokenInfo lastRemoved;

    public Board(int size,TextureAtlas textureAtlas){
        //Board Size
        this.size = size;
        squareSize = size/8;

        //Tokens
        this.tokenAtlas = textureAtlas;

        //Colocacion de piezas
        tokens =new TokenInfo[8][8];

        tokens[0][0] = new TokenInfo(Team.White, TokenType.Tower);
        tokens[1][1] = new TokenInfo(Team.White, TokenType.Pawn);
        tokens[3][0] = new TokenInfo(Team.White, TokenType.King);
        tokens[3][7]=new TokenInfo(Team.Black, TokenType.King);

        GenerateTexture();
    }


    //Este metodo dibuja la textura del tablero
    private void GenerateTexture(){
        int nextPow2 = Integer.highestOneBit(size-1)<<1;

        //Genera un canvas
        Pixmap pixmap = new Pixmap( nextPow2, nextPow2, Format.RGBA8888 );

        //Genera un cuadrado gris del tamaño de la pantalla
        pixmap.setColor(Color.GRAY);
        pixmap.fillRectangle(0, 0, size, size);

        //Cambiar el color del pincel
        pixmap.setColor(Color.WHITE);

        //Dibuja cada casilla blanca
        int y = 0;
        int x = 0;

        for(int i = 0; i < 32 ; i++){
            pixmap.fillRectangle(x * squareSize, y * squareSize, squareSize , squareSize);

            x += 2;

            if(x >= 8){
                y++;
                x = 1 - x % 8;
            }
        }

        //Asinga la textura
        boardTexture = new Texture( pixmap );
        pixmap.dispose();
        boardTextureRegion = new TextureRegion(boardTexture, 0, 0, size, size);
    }

    public void Draw(SpriteBatch batch){
        batch.draw(boardTextureRegion, 0, 0);

        for(int c = 0; c < 8; c++){
            for(int r = 0; r < 8; r++){
                DrawToken(batch, c, r);
            }
        }
    }

    private void DrawToken(SpriteBatch batch, int col, int row){
        //recibe la pieza
        TokenInfo info = tokens[col][row];
        if(info == null){return;}

        //Recibe el nombre de la pieza
        String name = info.GetTokenName();

        int h = 60;
        int w = 60;

        int X = col * squareSize + squareSize / 2 - h / 2;
        int Y = row * squareSize + squareSize / 2 - w / 2;


        batch.draw(tokenAtlas.findRegion(name), X , Y);
    }


    public TokenInfo GetPiece(IntPoint2D location){
        if(IsInBounds(location) == false){return null;}

        return tokens[location.getX()][location.getY()];
    }

    public void MovePiece(IntPoint2D from,IntPoint2D to)
    {
        if(from.equals(whiteKing)){
            whiteKing=to;
        }
        else if(from.equals(blackKing)){
            blackKing=to;
        }

        lastFrom=from;
        lastTo=to;
        lastRemoved= tokens[to.getX()][to.getY()];

        tokens[to.getX()][to.getY()]= tokens[from.getX()][from.getY()];
        tokens[from.getX()][from.getY()]=null;
    }

    public IntPoint2D GetPoint(int x, int y){
        return new IntPoint2D( x / squareSize, 7 - y / squareSize);
    }

    public IntRect GetRectangle(IntPoint2D point){
        return new IntRect(point.getX() * squareSize, (point.getY()) * squareSize, squareSize, squareSize);
    }

    public boolean IsInBounds(IntPoint2D location){
        return location.getX()<8&&location.getX()>=0&&location.getY()<8&&location.getY()>=0;
    }

}
