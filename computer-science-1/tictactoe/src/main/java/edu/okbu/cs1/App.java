package edu.okbu.cs1;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        int i;
        Game_Board gb = new Game_Board();              
        The_Game tg = new The_Game();

        gb.board();
        tg.initialize("inFile.txt");
        tg.play(gb.game);

        for (i= 0; i < tg.numGames; i++) {
            tg.winner = "no winner";
            gb.board();
            System.out.println();
            System.out.printf("Game %d\n",i+1);
            System.out.println(gb.Display());

            while (tg.winner.equals("no winner")) {
                tg.player1Turn(gb.game);
                System.out.println(gb.Display());
                System.out.println();
                tg.winner(gb.game);
                if (tg.winner.equals("X")){
                    break;
                }

                tg.player2Turn(gb.game);
                System.out.println(gb.Display());
                System.out.println();
                tg.winner(gb.game);
            }
            System.out.printf("The winner is %s\n",tg.winner);
        }
        System.out.printf("%s wins: %d\n",tg.player1.name, tg.player1.wins);
        System.out.printf("%s wins; %d\n",tg.player2.name,tg.player2.wins);
    }
}
