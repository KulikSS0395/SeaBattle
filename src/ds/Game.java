package ds;

import java.util.Random;

public class Game {
    public static void main(String[] args) {
        boolean gameOver = false;
        byte move;

        FieldGame player1 = new FieldGame();
        FieldGame player2 = new FieldGame();

        Random random = new Random();

        move = (byte) (random.nextInt(2)+1);
        {
            while (!gameOver) {
                switch (move) {
                    case 1:
                        if(player1.attack(player2)) {
                            move = 2;
                        }
                        if (player1.checkField(player2)){
                            gameOver = true;
                            System.out.println("Выиграл игрок: " + player1.getName());
                            break;
                        }
                        break;
                    case 2:
                        if (player2.attack(player1)) {
                            move = 1;
                        }
                        if (player2.checkField(player1)) {
                            gameOver = true;
                            System.out.println("Выиграл игрок: " + player2.getName());
                            break;
                        }
                        break;
                }
            }
        }
    }
}
