package cis3723.okbu.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.time.Instant;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class Game{
    int move_num;
    static Scanner scanner = new Scanner(System.in);
    ArrayList<Integer> move_history = new ArrayList<>();

    public void human_turn(GameState state, Move move1, Game game){
        
        
        
        //System.out.println("hi test");
        if (move1.player == 1){
        
        boolean reset = false;
        //while (reset == false) {
            //System.out.println("hi test2");
            //System.out.println(move1.player);
            if (move1.player == 1){
                System.out.print("Player 1 what is your move:");
            }
            else if (move1.player == 2){
                System.out.print("Player 2 what is your move:");
            }
            //System.out.println("test 2");
            int the_move = 0;
            
           try{
                the_move = get_int_from_user(scanner);//scanner.nextInt();
                System.out.println(the_move);
                //scanner.close();
            } catch(Exception e){
                System.out.println("not a int try agin");
                //reset = false;
            }
            //System.out.println("test 3");
            move1.colomn = the_move-1;
            /* 
            if (the_move < 1 && the_move > 7 ){
                System.out.print("not the right move(1,2,3,4,5,6,7) try agin");
                reset = false;
            }
            else{
                reset = true;
            }
            System.out.print(reset);
            */
        //}
        state = state.move(state, move1);
        move_history.add(move1.colomn);
        move_num += 1;
        move1.player = 2;
        
        state.display(state);
        /* to switch if two player
        if (move1.player == 1){
            move1.player = 2;

        }
        else if (move1.player == 2){
            move1.player = 1;
        }
        */
        System.out.println(move1.player);
        }
    
    
    }

    public void ai_turn(GameState state,Move move,Game game){
        if (move.player == 2){
        long start = timer_start("ai_turn");
        Random random = new Random();
        ArrayList<Move> moves = state.get_legal_moves(state);
        int random_index = random.nextInt(moves.size());
        int random_move = moves.get(random_index).colomn;
        random_move = rankmove(random_move);
        move.colomn = random_move;
        state = state.move(state, move);
        move_history.add(move.colomn);
        move_num += 1;
        move.player = 1;

        
        state.display(state);
        //System.out.println(move.player);
        
        double duration = timer_end(start, "ai_turn");
        String duration_string = String.valueOf(duration);
        String move_num_s = String.valueOf(move_num);
        String ai_move_s = String.valueOf(random_move);
        write_to_csv(move_num_s, duration_string, ai_move_s);
        }
    }

    public int rankmove(int move){
        Random random = new Random();
        int new_move =1 ;
        int the_random = random.nextInt(0,2);
        System.out.println("the move:" + move);
        System.out.println("the random: " + the_random);
        if (the_random == 1){
            if (move==0){
                new_move = 1;
            }
            else if (move==1){
                new_move = 2;
            }
            else if (move==2){
                new_move = 3;
            }
            else if (move==3){
                new_move = 3;
            }
            else if (move==4){
                new_move = 3;
            }
            else if (move==5){
                new_move = 4;
            }
            else if (move==6){
                new_move = 5;
            }
        }
        else if(the_random == 0){
            new_move = move;
        }
        System.out.println("the new move: " + new_move);
        return new_move;
    }

    public long timer_start(String function){
        long start_time = System.nanoTime();
        Instant ex_start_time = Instant.now();

        System.out.println(function + " started at: " + ex_start_time);
        return start_time;
    }

    public double timer_end(long start_time,String function){
        long end_time = System.nanoTime();
        long duration_ns = end_time - start_time;
        double duration_ms = duration_ns / 1000000.0;
        System.out.println(function + " took " + duration_ms + "ms to complete");
        return duration_ms;
    }

    public int get_int_from_user(Scanner scanner){
        while (!scanner.hasNextInt()) {
            System.out.println("not a vaid number try again");
            scanner.next();
        }
        int the_int = scanner.nextInt();
        return the_int;
    }

    public String get_String_from_user(Scanner scanner){
        return scanner.next();
    }

    public void write_to_csv(String move_num, String duration, String move){
        String file = "performance_log.csv";
        try(CSVWriter writer = new CSVWriter(new FileWriter(file, true))){
            String[] logRecord = {move_num, duration, move};
            writer.writeNext(logRecord);
        } catch (IOException e){
            e.printStackTrace();
        } 
    }

    public String serialize_state(GameState state){
        //String ser_game = state.display(state);
        int[][] board = state.board;
        StringBuilder sb = new StringBuilder("");
        sb.append(state.curent_player);
        sb.append(",");
        for (int i =board.length -1 ; i >= 0; i--){
            for (int j= 0; j< board[i].length; j++){
                //System.out.print(board[i][j]);
                //System.out.print(" ");
                sb.append(board[i][j]);
            }
            //System.out.println("");
            
        }
        //System.out.println("");
        return sb.toString();
    }

    public GameState deserialize_state(String string){
        String[] parts = string.split(",", 2);
        if (parts.length != 2){
            throw new IllegalArgumentException("Invalid game state");
        }

        GameState state = new GameState();
        state.curent_player = Integer.parseInt(parts[0]);

        String boardString = parts[1];
        if (boardString.length() != 42){
            throw new IllegalArgumentException("Invalid board data");
        }

        for (int row = 0; row < state.board.length; row++){
            for (int col = 0; col < state.board[row].length; col++){
                int index = row * state.board[row].length + col;
                int value = Character.digit(boardString.charAt(index), 10);
                if (value < 0 || value > 2){
                    throw new IllegalArgumentException("Invalid board value");
                }
                state.board[state.board.length - 1 - row][col] = value;
            }
        }

        return state;
    }
    public void game_create(){
        
    }
    public void game_play(GameState state, Game game, Move move1){
            move1.player = 1;
            state.display(state);
            while (state.is_terminal(state) == false){
                game.ai_turn(state, move1, game);
                game.human_turn(state,move1,game);
                System.out.println("Do you want to save this state? (y/n)");
                String y_n_save = get_String_from_user(scanner);
                if (y_n_save.equalsIgnoreCase("y")){
                    save_game(state);
                }
            }
    }

    public void save_game(GameState state){
        String saved_game = serialize_state(state);
        Path filePath = Paths.get("saved_games.txt");
        try {
            Files.writeString(filePath, saved_game, StandardOpenOption.APPEND);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public GameState load_game(){
        System.out.println("Input load game: ");
        String game = get_String_from_user(scanner);
        GameState new_game = deserialize_state(game);
        return new_game;
    }

    public static void main(String[] args){
        boolean game_next = true;
        while(game_next == true){
            Game game = new Game();
            Move move1 = new  Move();
            GameState state = new GameState();
            System.out.println("Do you want to load a game? (y/n)");
            if (game.get_String_from_user(scanner).equalsIgnoreCase("y")){
                GameState state_loaded = game.load_game();
                game.game_play(state_loaded, game, move1);
                String winner = state_loaded.check_winner(state_loaded);
                System.out.println(winner);
                System.out.println(game.move_history);
            } else{
                game.game_play(state,game,move1);
                String winner = state.check_winner(state);
                System.out.println(winner);
                System.out.println(game.move_history);
            }
            
            
            
            //Scanner scanner = new Scanner(System.in);
            System.out.print("Do you want to play another game? (y/n)");
            //String player_next = scanner.next();
            //scanner.close();
            String player_next = game.get_String_from_user(scanner);
            System.out.println(player_next);
            if (player_next.equalsIgnoreCase("y")){
                game_next = true;
                System.out.println("i work");
            }
            else{
                game_next = false;
            }
            
        }
    }
}
    

    
    
    
    
    //<S, M> implements Interface<S, M> {
    /* 
    public int turn;
    //GameState gameState = new state();

    @Override
    public S get_legal_moves(S state){
        return state;
    }

    @Override
    public S apply_move(S state, M move){
        return state;
    }

    @Override
    public boolean is_terminal(S state){
        return false;
    }

    @Override
    public int get_result(S state){
        return 0;
    }

    @Override
    public String get_current_player(int turn){
    //    return get_current_player();
        if (turn == 1) {
            return "player1";
        }
        if (turn == 2){
            return "player2";
        }
        return "unknown";

    }

    @Override
    public void display(S[][] state){
        for (i= 0; i < state.length; i++){
            for (j = 0; j < state[i].length; j++){
                System.out.print(state[i][j]+ " ");
            }

        }
       //System.out.print(Arrays.deepToString(state));
        }
       */
    
