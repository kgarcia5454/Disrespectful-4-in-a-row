import java.util.Scanner;


public class Main {
    public static void main (String[] args){

        //Currently rough code in order to program game. Once game is 
        //programmed, then this code will be formatted properly

        int [][] GameBoardArray = new int[6][7];
        int GameOver =0;

        for(int i = 0;i<=5;i++){
            for(int j = 0;j<=6;j++){
                GameBoardArray[i][j]=0;
            }
        }

        int player = 1;

        Scanner scan = new Scanner(System.in);

        System.out.println("\nDisrespectful 4 in a row! \n" );
        System.out.println("1.Play Classic");
        System.out.println("2.Quit");
        System.out.println("3.Disrespectful Mode");

        int GameMode = scan.nextInt();

        while(GameMode ==1){
            //printing out the board
            System.out.println("1 2 3 4 5 6 7");
            for(int[] row : GameBoardArray){
                printRow(row);
            }
            
            System.out.print("\nWhere do you wanna drop? ");

            int Dropped = scan.nextInt();

            if(Dropped>=1 && Dropped<=7){
                int A_Row = tokenCheck(GameBoardArray, Dropped,player);

                if(A_Row != 9){
                    GameOver = WinCheck(GameBoardArray, Dropped,player,A_Row);
                }   

                if(player == 1){
                    player++;
                }else{
                    player--;
                }
            }
            

            if(GameOver == 1){
                GameMode=2;
            }
            
        }

    
        if(GameMode == 2){
            scan.close();
            System.exit(0);
        }

        while(GameMode == 3){
                //printing out the board
                System.out.println("1 2 3 4 5 6 7");
                for(int[] row : GameBoardArray){
                    printRow(row);
                }
                System.out.println("Dropping or Moving");
                System.out.println("1. Dropping");
                System.out.println("2. Moving");

                int type = scan.nextInt();

                if(type==1) {
                    
                    System.out.print("Where do you wanna drop? ");
                    int Dropped = scan.nextInt();

                    if(Dropped>=1 && Dropped<=7){

                        int A_Row = tokenCheck(GameBoardArray, Dropped,player);

                        if(A_Row != 9){
                            GameOver = WinCheck(GameBoardArray, Dropped,player,A_Row);
                        }   

                        if(player == 1){
                            player++;
                        }else{
                            player--;
                        }
                    }
                }

                if(type==2){
                    System.out.print("\nWhich opponent piece do you want to move (Top Layer Only)");
                    int move = scan.nextInt();

                    if(move>=1 && move <=7){

                        int A_row = OpponentTokenCheck(GameBoardArray,move,player);
                        
                        if(A_row !=9){

                            if(player == 1){
                                player++;
                            }else{
                                player--;
                            }

                            System.out.println("Where do you want to move piece");

                            int new_col = scan.nextInt();

                            int B_Row = tokenCheck(GameBoardArray, new_col,player);

                            if(B_Row != 9){
                                GameOver = WinCheck(GameBoardArray, new_col,player,B_Row);
                            }   
                        }                    
                    }

                }

        }
    }

    public static void printRow(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }

    public static int tokenCheck(int [][] array, int selected, int play_Num){

        int tested = 0;
        int chk_row = 5;

        //Token placement
        while(tested==0){
            //Checks if slot is available. If it is not, it checks up one row
            if(array[chk_row][selected-1]==0){
                array[chk_row][selected-1] = play_Num;    
                tested=1;
            }else{
                if(chk_row > 0){
                    chk_row--;
                }else{
                    chk_row = 9;
                    System.out.println("FULL");
                    tested=1;
                }
            }
        }

        return chk_row;
    }

    public static int WinCheck(int [][] array, int selected, int player, int row){
        int H_win = 0;
        int V_win = 0;
        int UR_win = 0;
        int UL_win = 0;
        int O_Selected = selected;
        int O_Row = row;
        //regular directions
        boolean rightwards = false;
        boolean leftwards = false;
        boolean downwards = false;
        //Diagonals
        boolean upright = false;
        boolean downleft = false;

        boolean upleft = false;
        boolean downright=false;
        
        
        int tested = 0;
        int Winner=0;

        //horizontal Check
        while(tested==0){

            //Rightwards Check
            while(rightwards==false){
                if(array[row][selected-1]==player){
                    if(selected == 7){
                        rightwards=true;
                    }else{
                        selected++;
                    }
                    H_win++;
                }else if(array[row][selected-1]!=player){
                    rightwards = true; 
                }
            }

            //reset for Leftwards check
            selected = O_Selected;

            //Leftwards Check
            while(leftwards==false){
                if(array[row][selected-1]==player){
                    if(selected == 1){
                        leftwards=true;
                    }else{
                        selected--;
                    }
                    H_win++;
                }else if(array[row][selected-1]!=player){
                    leftwards = true; 
                }
            }
            //Removes 1 from the count as leftwards check recounts the initial token
            H_win--;

            //reset for Downwards check
            selected = O_Selected;

            //Downwards Check
            while(downwards==false){
                if(array[row][selected-1]==player){
                    if(row == 5){
                        downwards=true;
                    }else{
                        row++;
                    }
                    V_win++;
                }else if(array[row][selected-1]!=player){
                    downwards = true; 
                }
            }

            //Diagonals Check 
            //Part 1 (Up Right/Down Left)
            //reset for diagonals
            selected = O_Selected;
            row= O_Row;

            //Up Right Check
            while(upright==false){
                if(array[row][selected-1]==player){
                    if(row == 0 || selected==7){
                        upright=true;
                    }else{
                        row--;
                        selected++;
                    }
                    UR_win++;
                }else if(array[row][selected-1]!=player){
                    upright = true; 
                }
            }

             //reset for diagonals
            selected = O_Selected;
            row= O_Row;
            
            //Down Left Check
            while(downleft==false){
                if(array[row][selected-1]==player){
                    if(row == 5 || selected==1){
                        downleft=true;
                    }else{
                        row++;
                        selected--;
                    }
                    UR_win++;
                }else if(array[row][selected-1]!=player){
                    downleft = true; 
                }
            }
            //removing extra token from down left check 
            UR_win--;

            //Part 2 (Up Left/Down Right)

            //reset for diagonals
            selected = O_Selected;
            row= O_Row;

            //Up Left Check
            while(upleft==false){
                if(array[row][selected-1]==player){
                    if(row == 0 || selected==1){
                        upleft=true;
                    }else{
                        row--;
                        selected--;
                    }
                    UL_win++;
                }else if(array[row][selected-1]!=player){
                    upleft = true; 
                }
            }

             //reset for diagonals
            selected = O_Selected;
            row= O_Row;
            
            //Down Right Check
            while(downright==false){
                if(array[row][selected-1]==player){
                    if(row == 5 || selected==7){
                        downright=true;
                    }else{
                        row++;
                        selected++;
                    }
                    UL_win++;
                }else if(array[row][selected-1]!=player){
                    downright = true; 
                }
            }
            //removing extra token from down Right check 
            UL_win--;

            //ends loop
            tested=1;
        }


        if(H_win >= 4 || V_win >=4 || UR_win >= 4 || UL_win >=4){
            System.out.println("Player " + player+ " is the winner!");
            Winner=1;
        }

        return Winner;

    }

    public static int OpponentTokenCheck(int [][] array, int selected, int play_Num){
        int tested =0;
        int chk_row = 0;
        int Opponent = 9;


        //Searches for Opponents token on highest layer
        while(tested==0){
            //Checks player
            if(play_Num == 1 ){
                if(array[chk_row][selected-1]==2){
                    Opponent = chk_row;   
                    tested=1;
                }else{
                    if(chk_row < 5){
                        chk_row++;
                    }else{

                        tested=1;
                    }
                }
            }

            if(play_Num == 2 ){
                if(array[chk_row][selected-1]==1){
                    Opponent = chk_row;   
                    tested=1;
                }else{
                    if(chk_row < 5){
                        chk_row++;
                    }else{
                        tested=1;
                    }
                }
            }
        }

        if(Opponent==9){
            System.out.println("no token found");
        }else{
            System.out.println("token found at "+ selected + "," + Opponent);
            array[Opponent][selected-1] = 0;
        }

        return Opponent;

    }
}