package com.cowingtonpost;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer implements Player{

    public String name = "Computer";
    public BoardPiece boardPiece = BoardPiece.EMPTY;
    private AiAlgorithm algorithm;
    private enum AiAlgorithm {
        RANDOM,
        AI,
    }

    public ComputerPlayer() {
        algorithm = AiAlgorithm.values()[ThreadLocalRandom.current().nextInt(AiAlgorithm.values().length)];
        System.out.println("Using algorithm " + algorithm);
    }


    private int minimax(BoardPiece[][] board, BoardPiece player){
        boolean self_win = Game.checkWinner(player, board);
        boolean opponent_win = Game.checkWinner(player, board);
        boolean tie = Game.checkTie(board);
        if (self_win || opponent_win || tie){
            if (self_win) {
                return 1;
            }
            if (opponent_win) {
                return -1;
            }
            if (tie) {
                return 0;
            }
        }

        int move = -1;
        int score = -2;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (board[y][x] == BoardPiece.EMPTY) {
                    BoardPiece[][] boardWithNewMove = board;
                    int scoreForTheMove;
                    if (boardPiece == BoardPiece.X) {
                        scoreForTheMove = -minimax(boardWithNewMove, BoardPiece.O);
                    } else {
                        scoreForTheMove = -minimax(boardWithNewMove, BoardPiece.X);
                    }

                    if (scoreForTheMove > score) {
                        score = scoreForTheMove;
                        move = (y*3) + (x+1);
                    }
                }
            }
        }

        if (move == -1) {
            return 0; // No move - it's a draw
        }
        return score;
    }

    private int getValue(BoardPiece piece) {
        switch (piece) {
            case X:
                return 1;
            case EMPTY:
                return 0;
            case O:
                return -1;
        }
        return 0;
    }

    @Override
    public BoardPiece[][] playTurn(BoardPiece[][] board) {
        switch(algorithm) {
            case RANDOM:
                while (true) {
                    int x = ThreadLocalRandom.current().nextInt(3);
                    int y = ThreadLocalRandom.current().nextInt(3);

                    if (board[y][x] == BoardPiece.EMPTY) {
                        board[y][x] = boardPiece;
                        return board;
                    }


                }

            case AI:
                if (board[1][1] == BoardPiece.EMPTY) {
                    board[1][1] = boardPiece;
                    return board;
                }

                if (board[0][0] == BoardPiece.EMPTY) {
                    board[0][0] = boardPiece;
                    return board;
                }

                if (board[0][2] == BoardPiece.EMPTY) {
                    board[0][2] = boardPiece;
                }

                if (board[2][0] == BoardPiece.EMPTY) {
                    board[2][0] = boardPiece;
                }

                if (board[2][2] == BoardPiece.EMPTY) {
                    board[2][2] = boardPiece;
                }




                while (true) {
                    int x = ThreadLocalRandom.current().nextInt(3);
                    int y = ThreadLocalRandom.current().nextInt(3);

                    if (board[y][x] == BoardPiece.EMPTY) {
                        board[y][x] = boardPiece;
                        return board;
                    }


                }
//            case MINIMAX:
//                int position = minimax(board, boardPiece);
//                int x = position % 3;
//                int y = (position - x) / 3;
//
//                board[y][x] = boardPiece;
//                return board;

        }
        return board;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public boolean hasWon(BoardPiece[][] board) {
        return false;
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
        return PlayerType.COMPUTER;
    }
}
