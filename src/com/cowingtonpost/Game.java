package com.cowingtonpost;

import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;

    private BoardPiece[][] board;

    int Player1Wins = 0;
    int Player2Wins = 0;
    int ties = 0;

    public static boolean checkWinner(BoardPiece piece, BoardPiece[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == piece && board[i][1] == piece && board[i][2] == piece) {
                return true;
            }
            if (board[0][i] == piece && board[1][i] == piece && board[2][i] == piece) {
                return true;
            }

        }
        if (board[0][0] == piece && board[1][1] == piece && board[2][2] == piece) {
            return true;
        }
        if (board[0][2] == piece && board[1][1] == piece && board[2][0] == piece) {
            return true;
        }
        return false;
    }

    private void printSummary() {
        System.out.println("Summary");
        System.out.println("Player 1: " + String.valueOf(Player1Wins) + " win(s)");
        System.out.println("Player 2: " + String.valueOf(Player2Wins) + " win(s)");
        System.out.println("Tied: " + String.valueOf(ties));
    }

    public static boolean checkTie(BoardPiece[][] board) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (board[y][x] == BoardPiece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < 3; y++) {
            output.append("|");
            for (int x = 0; x < 3; x++) {
                if (board[y][x] == BoardPiece.EMPTY) {
                    output.append(" ").append((y * 3) + (x + 1)).append(" |");
                } else {
                    output.append(" ").append(board[y][x]).append(" |");
                }
            }
            output.append("\n-------------\n");
        }
        System.out.println(output);
    }

    private void resetBoard() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                board[y][x] = BoardPiece.EMPTY;
        }
    }}

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        board = new BoardPiece[3][3];
        resetBoard();
        printBoard();
        int input2;



        System.out.println("What type of player is player 1 (Human: 1 , Computer: 2) ?");
        input2 = scanner.nextInt();

        if (input2 == 1) {
            player1 = new HumanPlayer();
        } else if (input2 == 2) {
            player1 = new ComputerPlayer();
        }
        player1.setBoardPiece(BoardPiece.X);


        // Player 2
        System.out.println("What type of player is player 2 (Human: 1, Computer: 2) ?");
        input2 = scanner.nextInt();

        if (input2 == 1) {
            player2 = new HumanPlayer();

            player2.setName(scanner.nextLine().strip());
        } else if (input2 == 2) {
            player2 = new ComputerPlayer();
        }
        player2.setBoardPiece(BoardPiece.O);

        int numGames = -1;
        int gamesPlayed = 0;
        if (player1.getPlayerType() == PlayerType.COMPUTER && player2.getPlayerType() == PlayerType.COMPUTER) {
            System.out.println("Enter how many games to play.");
            numGames = scanner.nextInt();
        }
        // Game loop
        while (true) {
            printBoard();
            System.out.println("Player 1 it is your turn, please select a square");
            board = player1.playTurn(board);
            printBoard();

            if (Game.checkWinner(BoardPiece.X, board)) {
                printBoard();
                System.out.println("Player 1 wins");
                Player1Wins++;
                gamesPlayed++;
                if (numGames <= gamesPlayed && numGames != -1) {
                    printSummary();
                    System.exit(0);
                }
                if (numGames != -1 ) {
                    resetBoard();
                    continue;
                }
                System.out.println("Do you want to play again? 1 for yes and 2 for no");
                int input = scanner.nextInt();
                switch (input) {
                    case 1:
                        resetBoard();

                        continue;
                    default:
                        printSummary();
                        System.exit(0);
                }
            }
            if (checkTie(board)) {
                printBoard();
                System.out.println("Its a tie!");
                ties ++;
                gamesPlayed++;
                if (numGames <= gamesPlayed && numGames != -1) {
                    printSummary();
                    System.exit(0);
                }
                if (numGames != -1 ) {
                    resetBoard();
                    continue;
                }
                System.out.println("Do you want to play again 1 for yes and 2 for no");
                int input = scanner.nextInt();
                switch (input) {
                    case 1:
                        resetBoard();
                        continue;
                    default:
                        printSummary();
                        System.exit(0);
                }
            }
            System.out.println("Player 2 it is your turn, please select a square");
            board = player2.playTurn(board);


            if (Game.checkWinner(BoardPiece.O, board)) {
                printBoard();

                System.out.println("Player 2 wins");
                Player2Wins++;
                gamesPlayed++;
                if (numGames <= gamesPlayed && numGames != -1) {
                    printSummary();
                    System.exit(0);
                }
                if (numGames != -1 ) {
                    resetBoard();
                    continue;
                }
                System.out.println("Do you want to play again? 1 for yes and 2 for no");
                switch (scanner.nextInt()) {
                    case 1:
                        resetBoard();
                        continue;
                    default:

                        printSummary();
                        System.exit(0);

                }
            }
            if (checkTie(board)) {
                printBoard();
                System.out.println("Its a tie!");
                ties++;
                gamesPlayed++;
                if (numGames <= gamesPlayed && numGames != -1) {
                    printSummary();
                    System.exit(0);
                }
                if (numGames != -1 ) {
                    resetBoard();
                    continue;
                }
                System.out.println("Do you want to play again 1 for yes and 2 for no");
                switch (scanner.nextInt()) {
                    case 1:
                        resetBoard();
                        continue;
                    default:
                        printSummary();
                        System.exit(0);
                }
            }

        }
    }
}
