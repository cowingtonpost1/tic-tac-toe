package com.cowingtonpost;

import java.util.Scanner;

public class Main {

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);

        BoardPiece[][] board = new BoardPiece[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                board[y][x] = BoardPiece.EMPTY;
            }
        }

        String input;
        int input2;



        System.out.println("What type of player is player 1 (Human: 1 , Computer: 2) ?\n");
        input2 = scanner.nextInt();

        if (input2 == 1) {
            HumanPlayer player1 = new HumanPlayer();

            player1.setName(scanner.nextLine().strip());
        } else if (input2 == 2) {
            ComputerPlayer player1 = new ComputerPlayer();
        }


        // Player 2
        System.out.println("What type of player is player 2 (Human: 1, Computer: 2) ?\n");
        input2 = scanner.nextInt();

        if (input2 == 1) {
            HumanPlayer player2 = new HumanPlayer();

            player2.setName(scanner.nextLine().strip());
        } else if (input2 == 2) {
            ComputerPlayer player2 = new ComputerPlayer();
        }
        // Game loop
        while (true) {



        }


    }

    public static void main(String[] args) {
//        playGame();
        Game game = new Game();
        game.playGame();
    }
}
