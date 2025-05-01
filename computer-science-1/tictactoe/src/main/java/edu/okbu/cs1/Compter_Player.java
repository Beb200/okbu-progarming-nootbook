package edu.okbu.cs1;

public class Compter_Player {

    public void com_player(String game[][]){

        if ((game[0][0].equals("O")) && (game[0][1].equals("O")) && (game[0][2].equals("#"))){
            game[0][2] = "O";
            //O|O|#
            //#|#|#
            //#|#|#
        }
        else if ((game[1][0].equals("O")) && (game[1][1].equals("O")) && (game[1][2].equals("#"))){
            game[1][2] = "O";
            //#|#|#
            //O|O|#
            //#|#|#
        }
        else if ((game[2][0].equals("O")) && (game[2][1].equals("O")) && (game[2][2].equals("#"))){
            game[2][2] = "O";
            //#|#|#
            //#|#|#
            //O|O|#
        }


        else if ((game[0][1].equals("O")) && (game[0][2].equals("O")) && (game[0][0].equals("#"))){
            game[0][0] = "O";
            //#|O|O
            //#|#|#
            //#|#|#
        }
        else if ((game[1][1].equals("O")) && (game[1][2].equals("O")) && (game[1][0].equals("#"))){
            game[1][0] = "O";
            //#|#|#
            //#|O|O
            //#|#|#
        }
        else if ((game[2][1].equals("O")) && (game[2][2].equals("O")) && (game[2][0].equals("#"))){
            game[2][0] = "O";
            //#|#|#
            //#|#|#
            //#|O|O
        }


        else if ((game[0][0].equals("O")) && (game[1][0].equals("O")) && (game[2][0].equals("#"))){
            game[2][0] = "O";
            //O|#|#
            //O|#|#
            //#|#|#
        }
        else if ((game[0][1].equals("O")) && (game[1][1].equals("O")) && (game[2][1].equals("#"))){
            game[2][1] = "O";
            //#|O|#
            //#|O|#
            //#|#|#
        }
        else if ((game[0][2].equals("O")) && (game[1][2].equals("O")) && (game[2][2].equals("#"))){
            game[2][2] = "O";
            //#|#|O
            //#|#|O
            //#|#|#
        }


        else if ((game[2][0].equals("O")) && (game[1][1].equals("O")) && (game[0][2].equals("#"))){
            game[0][2] = "O";
            //#|#|#
            //#|O|#
            //O|#|#
        }
        else if ((game[0][2].equals("O")) && (game[1][1].equals("O")) && (game[2][0].equals("#"))){
            game[2][0] = "O";
            //#|#|O
            //#|O|#
            //#|#|#
        }
        else if ((game[0][0].equals("O")) && (game[1][1].equals("O")) && (game[2][2].equals("#"))){
            game[2][2] = "O";
            //O|#|#
            //#|O|#
            //#|#|#
        }
        else if ((game[1][1].equals("O")) && (game[2][2].equals("O")) && (game[0][0].equals("#"))){
            game[0][0] = "O";
            //#|#|#
            //#|O|#
            //#|#|O
        }

        else if ((game[1][0].equals("O")) && (game[2][0].equals("O")) && (game[0][0].equals("#"))){
            game[0][0] = "O";
            //#|#|#
            //O|#|#
            //O|#|#
        }
        else if ((game[1][1].equals("O")) && (game[2][1].equals("O")) && (game[0][1].equals("#"))){
            game[0][1] = "O";
            //#|#|#
            //#|O|#
            //#|O|#
        }
        else if ((game[1][2].equals("O")) && (game[2][2].equals("O")) && (game[0][2].equals("#"))){
            game[0][2] = "O";
            //#|#|#
            //#|#|O
            //#|#|O
        }


        else if ((game[0][0].equals("O")) && (game[0][2].equals("O")) && (game[0][1].equals("#"))){
            game[0][1] = "O";
            //O|#|O
            //#|#|#
            //#|#|#
        }
        else if ((game[1][0].equals("O")) && (game[1][2].equals("O")) && (game[1][1].equals("#"))){
            game[1][1] = "O";
            //#|#|#
            //O|#|O
            //#|#|#
        }
        else if ((game[2][0].equals("O")) && (game[2][2].equals("O")) && (game[2][1].equals("#"))){
            game[2][1] = "O";
            //#|#|#
            //#|#|#
            //O|#|O
        }


        else if ((game[0][0].equals("O")) && (game[2][0].equals("O")) && (game[1][0].equals("#"))){
            game[1][0] = "O";
            //O|#|#
            //#|#|#
            //O|#|#
        }
        else if ((game[0][1].equals("O")) && (game[2][1].equals("O")) && (game[1][1].equals("#"))){
            game[1][1] = "O";
            //#|O|#
            //#|#|#
            //#|O|#
        }
        else if ((game[0][2].equals("O")) && (game[2][2].equals("O")) && (game[1][2].equals("#"))){
            game[1][2] = "O";
            //#|#|O
            //#|#|#
            //#|#|O
        }


        else if ((game[0][0].equals("O")) && (game[2][2].equals("O")) && (game[1][1].equals("#"))){
            game[1][1] = "O";
            //O|#|#
            //#|#|#
            //#|#|O
        }
        else if ((game[2][0].equals("O")) && (game[0][2].equals("O")) && (game[1][1].equals("#"))){
            game[1][1] = "O";
            //#|#|O
            //#|#|#
            //O|#|#
        }




        if ((game[0][0].equals("X")) && (game[0][1].equals("X")) && (game[0][2].equals("#"))){
            game[0][2] = "O";
            //X|X|#
            //#|#|#
            //#|#|#
        }
        else if ((game[1][0].equals("X")) && (game[1][1].equals("X")) && (game[1][2].equals("#"))){
            game[1][2] = "O";
            //#|#|#
            //X|X|#
            //#|#|#
        }
        else if ((game[2][0].equals("X")) && (game[2][1].equals("X")) && (game[2][2].equals("#"))){
            game[2][2] = "O";
            //#|#|#
            //#|#|#
            //X|X|#
        }


        else if ((game[0][1].equals("X")) && (game[0][2].equals("X")) && (game[0][0].equals("#"))){
            game[0][0] = "O";
            //#|X|X
            //#|#|#
            //#|#|#
        }
        else if ((game[1][1].equals("X")) && (game[1][2].equals("X")) && (game[1][0].equals("#"))){
            game[1][0] = "O";
            //#|#|#
            //#|X|X
            //#|#|#
        }
        else if ((game[2][1].equals("X")) && (game[2][2].equals("X")) && (game[2][0].equals("#"))){
            game[2][0] = "O";
            //#|#|#
            //#|#|#
            //#|X|X
        }


        else if ((game[0][0].equals("X")) && (game[1][0].equals("X")) && (game[2][0].equals("#"))){
            game[2][0] = "O";
            //X|#|#
            //X|#|#
            //#|#|#
        }
        else if ((game[0][1].equals("X")) && (game[1][1].equals("X")) && (game[2][1].equals("#"))){
            game[2][1] = "O";
            //#|X|#
            //#|X|#
            //#|#|#
        }
        else if ((game[0][2].equals("X")) && (game[1][2].equals("X")) && (game[2][2].equals("#"))){
            game[2][2] = "O";
            //#|#|X
            //#|#|X
            //#|#|#
        }


        else if ((game[2][0].equals("X")) && (game[1][1].equals("X")) && (game[0][2].equals("#"))){
            game[0][2] = "O";
            //#|#|#
            //#|X|#
            //X|#|#
        }
        else if ((game[0][2].equals("X")) && (game[1][1].equals("X")) && (game[2][0].equals("#"))){
            game[2][0] = "O";
            //#|#|X
            //#|X|#
            //#|#|#
        }
        else if ((game[0][0].equals("X")) && (game[1][1].equals("X")) && (game[2][2].equals("#"))){
            game[2][2] = "O";
            //X|#|#
            //#|X|#
            //#|#|#
        }
        else if ((game[1][1].equals("X")) && (game[2][2].equals("X")) && (game[0][0].equals("#"))){
            game[0][0] = "O";
            //#|#|#
            //#|X|#
            //#|#|X
        }

        else if ((game[1][0].equals("X")) && (game[2][0].equals("X")) && (game[0][0].equals("#"))){
            game[0][0] = "O";
            //#|#|#
            //X|#|#
            //X|#|#
        }
        else if ((game[1][1].equals("X")) && (game[2][1].equals("X")) && (game[0][1].equals("#"))){
            game[0][1] = "O";
            //#|#|#
            //#|X|#
            //#|X|#
        }
        else if ((game[1][2].equals("X")) && (game[2][2].equals("X")) && (game[0][2].equals("#"))){
            game[0][2] = "O";
            //#|#|#
            //#|#|X
            //#|#|X
        }


        else if ((game[0][0].equals("X")) && (game[0][2].equals("X")) && (game[0][1].equals("#"))){
            game[0][1] = "O";
            //X|#|X
            //#|#|#
            //#|#|#
        }
        else if ((game[1][0].equals("X")) && (game[1][2].equals("X")) && (game[1][1].equals("#"))){
            game[1][1] = "O";
            //#|#|#
            //X|#|X
            //#|#|#
        }
        else if ((game[2][0].equals("X")) && (game[2][2].equals("X")) && (game[2][1].equals("#"))){
            game[2][1] = "O";
            //#|#|#
            //#|#|#
            //X|#|X
        }


        else if ((game[0][0].equals("X")) && (game[2][0].equals("X")) && (game[1][0].equals("#"))){
            game[1][0] = "O";
            //X|#|#
            //#|#|#
            //X|#|#
        }
        else if ((game[0][1].equals("X")) && (game[2][1].equals("X")) && (game[1][1].equals("#"))){
            game[1][1] = "O";
            //#|X|#
            //#|#|#
            //#|X|#
        }
        else if ((game[0][2].equals("X")) && (game[2][2].equals("X")) && (game[1][2].equals("#"))){
            game[1][2] = "O";
            //#|#|X
            //#|#|#
            //#|#|X
        }


        else if ((game[0][0].equals("X")) && (game[2][2].equals("X")) && (game[1][1].equals("#"))){
            game[1][1] = "O";
            //X|#|#
            //#|#|#
            //#|#|X
        }
        else if ((game[2][0].equals("X")) && (game[0][2].equals("X")) && (game[1][1].equals("#"))){
            game[1][1] = "O";
            //#|#|X
            //#|#|#
            //X|#|#
        }


        else if (game[1][1].equals("#")){
            game[1][1] = "O";
        }


        else if (game[0][0].equals("#")){
            game[0][0] = "O";
        }
        else if (game[0][2].equals("#")){
            game[0][2] = "O";
        }
        else if (game[2][0].equals("#")){
            game[2][0] = "O";
        }
        else if (game[2][2].equals("#")){
            game[2][2] = "O";
        }


        else if (game[0][1].equals("#")){
            game[0][1] = "O";
        }
        else if (game[1][0].equals("#")){
            game[1][2] = "O";
        }
        else if (game[2][1].equals("#")){
            game[2][1] = "O";
        }

    }
}
