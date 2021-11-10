package com.cowingtonpost;

public interface Player {
    public BoardPiece[][] playTurn(BoardPiece[][] board);
    public String getName();
    public void setName(String name);
    public boolean hasWon(BoardPiece[][] board);

    public BoardPiece getBoardPiece();
    public void setBoardPiece(BoardPiece boardPiece);

    public PlayerType getPlayerType();
}
