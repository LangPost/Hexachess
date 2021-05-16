package com.agonzalez.hexchess;

enum Team{White, Black}

enum TokenType {
    Pawn,
    Bishop,
    Knight,
    Tower,
    King,
    Queen
}

public class TokenInfo {
    Team team;
    TokenType type;

    public TokenInfo(Team team, TokenType type) {
        super();
        this.team = team;
        this.type = type;
    }

    //Envia el nombre de la pieza con el siguiente formato: WhitePawn
    public String GetTokenName(){
        if(team == Team.White){return "White" + type.toString();}
        if(team == Team.Black){return "Black" + type.toString();}

        return "Error";
    }
}
