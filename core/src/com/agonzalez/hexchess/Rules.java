package com.agonzalez.hexchess;

import java.util.ArrayList;

public class Rules {

    static void GetValidMoves(ArrayList<IntPoint2D> list, IntPoint2D selection, TokenInfo piece, Board board){
        switch(piece.type){
            case Pawn: GetValidMovesPawn(list,selection,piece.team,board); break;
            case Tower: GetValidMovesCastle(list,selection,piece.team,board); break;
            case King:  GetValidMovesKing(list,selection,piece.team,board); break;
        }
    }

    private static void GetValidMovesKing(ArrayList<IntPoint2D> list,IntPoint2D selection,Team team,Board board){
        for(int xDir=-1;xDir<=1;xDir++){
            for(int yDir=-1;yDir<=1;yDir++){

                if(xDir == 0 && yDir == 0){continue;}

                IntPoint2D move=selection.Transpose(xDir, yDir);
                TokenInfo target=board.GetPiece(move);

                if(board.IsInBounds(move)&&(target==null||target.team!=team)){list.add(move);}
            }
        }

    }


    private static void GetValidMovesCastle(ArrayList<IntPoint2D> list,IntPoint2D selection,Team team,Board board) {
        for(int direction=0;direction<2;direction++) {

            for(int direction2=-1;direction2<=1;direction2+=2) {
                IntPoint2D move=selection;
                while(true){

                    if(direction==0){
                        move=move.Transpose(direction2, 0);
                    } else {
                        move=move.Transpose(0, direction2);
                    }

                    if(!board.IsInBounds(move)) { break; }

                    TokenInfo target= board.GetPiece(move);

                    if(target!=null) {
                        if(target.team!=team) { list.add(move); }
                        break;
                    }

                    list.add(move);
                }
            }

        }
    }


    private static void GetValidMovesPawn(ArrayList<IntPoint2D> list,IntPoint2D selection,Team team,Board board) {
        int direction=(team==Team.White)?1:-1;

        IntPoint2D normalMove=selection.Transpose(0, direction);

        if(board.IsInBounds(normalMove)&&board.GetPiece(normalMove)==null) {  list.add(normalMove); }

        int startRow=(team==Team.White)?1:6;

        IntPoint2D firstMove=selection.Transpose(0, 2*direction);

        if(selection.getY()==startRow&&board.IsInBounds(firstMove)&&board.GetPiece(firstMove)==null) { list.add(firstMove); }

        for(int i=-1;i<=1;i+=2) {
            IntPoint2D captureMove=selection.Transpose(i, direction);

            TokenInfo target=board.GetPiece(captureMove);

            if(board.IsInBounds(captureMove)&&target!=null&&target.team!=team) {list.add(captureMove);}
        }

    }
}
