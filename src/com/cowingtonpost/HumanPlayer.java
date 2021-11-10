package com.cowingtonpost;

import java.util.Scanner;

public class HumanPlayer implements Player{
    public String name = "Human";
    private BoardPiece boardPiece = BoardPiece.EMPTY;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean hasWon(BoardPiece[][] board) {
        return false;
    }

    @Override
    public BoardPiece[][] playTurn(BoardPiece[][] board) {
        Scanner s = new Scanner(System.in);
        while (true) {
            int position = s.nextInt()-1;

            int x = position % 3;
            int y = (position - x) / 3;

            if (y >= 0 && y < 4 && x >= 0) {
                if (board[y][x] == BoardPiece.EMPTY) {
                    board[y][x] = boardPiece;
                    return board;
                } else {
                    System.out.println("That square has already been taken.");
                }
            } else {
                System.out.println("Invalid square");
            }


        }
    }

    @Override
    public String getName() {
        return name;
    }

    public HumanPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is this player's name?");
        String name = scanner.nextLine().strip();

        setName(name);
    }

    @Override
    public BoardPiece getBoardPiece() {
        return boardPiece;
    }

    @Override
    public void setBoardPiece(BoardPiece boardPiece) {
        this.boardPiece = boardPiece;
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.HUMAN;
    }
}
