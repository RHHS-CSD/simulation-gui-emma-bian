/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simulation;
import java.util.*;

/**
 * Predator and Prey Simulation Program
 *
 * @author emmabian
 * @version Sept 23, 2024
 */
public class Simulation {

    Scanner kb = new Scanner(System.in);
    Random r = new Random();
    
    /**
     * main method controls the general flow of the code
     * determines which tasks should be performed when
     * calls different methods to perform those tasks
     * 
     * @param args the command line arguments
     */
        
    //initialize all variables
    int row = 20;
    int col = 20;
    int[][] grid = new int[row][col];
    int[][] newGrid = new int[row][col];
    int numPredator = 10;
    int numPrey = 100;
    ArrayList<Integer> a = new ArrayList<>(); //the number of steps ago that the predator last ate a prey. if <= 10, can reproduce, if > 20, will die
    //initialized to 11 to limit reproduction before eating any prey

//     String userIn;
    
//        //ask for user input
//        displayIns();
//        userIn = getInput();
//
//        //loop the simulation until the user enters "q" to quit
//        while (!userIn.equalsIgnoreCase("q")) {
//            
//            //if it is the first round, "s" is entered to begin the simulation
//            if (userIn.equalsIgnoreCase("s")) {
//                //reset (clear) grid to begin
//                resetGrid(grid);
//                resetGrid(newGrid);
//                
//                //a new grid is generated and displayed
//                generateNew(grid, numPredator, numPrey);
//                displayGrid(grid);
//                
//                //go to next round and wait for user input to continue updating or quit
//                userIn = kb.nextLine();
//                continue;
//            }
//            
//            //when the user enters any other intacter, the grid will update
//            updateGrid(grid, newGrid);
//            displayGrid(grid);
//            
//            userIn = getInput();
//        }
    
//    private void displayIns() {
//        System.out.println("Enter: q to quit; s to start; any other character to display updated grid");
//    }
    
//    private String getInput() {
//        
//        //get input from user
//        return kb.nextLine();
//    }
    
    protected void resetGrid(int[][] grid) {
        
        //for every spot on the grid, represent it as a " " space to indicate empty
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                grid[i][j] = -2;
            }
        }
    }
    
//    private void displayGrid (int[][] grid) {
//        //print the whole grid to the screen
//        for (int i=0;i<grid.length;i++) {
//            for (int j=0;j<grid[0].length;j++) {
//                
//                //-2 means that there is a blank space, -1 means that there is a prey, all positive numbers indicate predator
//                if (grid[i][j] == -2) {
//                    System.out.print(" ");
//                } else if (grid[i][j] == -1) {
//                    System.out.print("o");
//                } else if (grid[i][j] > 0) {
//                    System.out.print("W");
//                }
//                
//            }
//            System.out.print("\n");
//        }
//    }
    
    protected void displayGrid (int[][] grid) {
        
    }
    
    protected void generateNew (int[][] grid, int numPredator, int numPrey) {
        int row, col;
        
        //generate all prey
        for (int i=0;i<numPrey;i++) {
            
            //loop until an empty spot is found and add the new prey (o) to that spot
            do {
                row = r.nextInt(grid.length);
                col = r.nextInt(grid[0].length);
            } while (grid[row][col] != -2);
            grid[row][col] = -1;
        }
        
        //generate all predators
        for (int i=0;i<numPredator;i++) {
            
            //loop until an empty spot is found and then add a new predator
            //positive numbers that tracks the amount of steps they took after eating their last prey (starts a 1)
            //predator will die if their count is greater than 15
            do {
                row = r.nextInt(grid.length);
                col = r.nextInt(grid[0].length);
            } while (grid[row][col] != -2 && grid[row][col] != -1);
            grid[row][col] = 1;
        }
    }
    
    protected void updateGrid (int[][] grid, int[][] newGrid) {
        
        //copy the grid onto the new grid
        //updating feature looks at the orginal grid to check if each spot is a predator, prey, or blank
        //then it looks around to see if there are free spots on the new grid, and updates the new grid
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++) {
                newGrid[i][j] = grid[i][j];
            }
        }
        
        //for every spot on the grid
        for (int i=0;i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                
                //if there is a prey, move the prey based on availability
                if (grid[i][j] == -1) {
                    checkAvailablePrey(grid, newGrid, i, j);
                    
                //if there is a predator, move it based on availability
                } else if (grid[i][j] > -1) {
                    checkAvailablePredator(grid, newGrid, i, j);
                }
                
                //if the space is empty, do nothing
            }
            
        }
        
        //copy the updated grid onto "grid"
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++) {
                grid[i][j] = newGrid[i][j];
            }
        }
        resetGrid(newGrid);
    }
    
    private void checkAvailablePrey(int[][] grid, int[][] newGrid, int row, int col){
        int[] order = shuffleArrPrey();
        int moveDir; //0: up, 1: right, 2: down, 3: left
        
        //randomly generate the direction a prey moves and test if that direction is available
        for(int i=0;i<order.length;i++) {
            moveDir = order[i];
            
            //move up
            if (moveDir == 0){
                
                //consider edge cases, simulate a toroidal grid
                int ro = lookUp(grid, row, col);
                
                //check if the space above the spot of the prey is free
                if (newGrid[ro-1][col] == -2){
                    
                    //if it is free, determine if it will reproduce or move (1 in 10 chance of reproduction)
                    if (r.nextInt(10) == 0) {
                        reproducePrey(newGrid, row, col, (ro-1), col);
                    } else {
                        movePrey(newGrid, row, col, (ro-1), col);
                    }
                    return;
                }
                
            //move right
            } else if (moveDir == 1) {
                
                //consider edge cases, simulate a toroidal grid
                int c = lookRight(grid, row, col);
                if (c != col) {
                    return;
                }
                
                //check if the space to the right is free
                if (newGrid[row][c+1] == -2){
                    
                    //if so, move or reproduce to occupy the spot on the right
                    if (r.nextInt(10) == 0) {
                        reproducePrey(newGrid, row, col, row, (c+1));
                    } else {
                        movePrey(newGrid, row, col, row, (c+1));
                    }
                    return;
                }
                
            //move down
            } else if (moveDir == 2) {
                
                //consider edge cases, simulate a toroidal grid
                int ro = lookDown(grid, row, col);
                if (ro != row) {
                    return;
                }
                
                //check if the space below is free
                if (newGrid[ro+1][col] == -2){
                    
                    //if so, move or reproduce to occupy the spot below it
                    if (r.nextInt(10) == 0) {
                        reproducePrey(newGrid, row, col, (ro+1), col);
                    } else {
                        movePrey(newGrid, row, col, (ro+1), col);
                    }
                    return;
                }
                
            //move left
            } else if (moveDir == 3) {
                
                //consider edge cases, simulate a toroidal grid
                int c = lookLeft(grid, row, col);
                
                
                //check if the space to the left is free
                if (newGrid[row][c-1] == -2){
                    
                    //if so, move or reproduce
                    if (r.nextInt(10) == 0) {
                        reproducePrey(newGrid, row, col, row, (c-1));
                    } else {
                        movePrey(newGrid, row, col, row, (c-1));
                    }
                    return;
                }
                
            } //end if
        }
        
        //if the prey has no room to move, it will die
        deceasePrey(newGrid, row, col);
        
    } //end method
    
    private void checkAvailablePredator (int[][] grid, int[][] newGrid, int row, int col) {
        int[] order = shuffleArrPredator();
        int moveDir, eatDir; //0: up, 1: right, 2: down, 3: left
        
        //randomly (direction wise if there is more than one) move to an adjacent box with a prey
        for (int i=0;i<order.length;i++){
            eatDir = order[i];
            
            //move up
            if (eatDir == 0){
                
                //consider edge cases, simulate a toroidal grid
                int ro = lookUp(grid, row, col);
                
                //check if the space above the spot of the predator has a prey
                if (newGrid[ro-1][col] == -1){
                    eatPrey(grid, newGrid, row, col, ro-1, col);
                    return;
                }
                
            //move right
            } else if(eatDir == 1) {
                //consider edge cases, simulate a toroidal grid
                int c = lookRight(grid, row, col);
                
                //check if the space to the right has a prey
                if (newGrid[row][c+1] == -1){
                    eatPrey(grid, newGrid, row, col, row, c+1);
                    return;
                }
                
            //move down
            } else if(eatDir == 2) {
                
                //consider edge cases, simulate a toroidal grid
                int ro = lookDown(grid, row, col);
                
                //check if the space below has prey
                if (newGrid[ro+1][col] == -1){
                    eatPrey(grid, newGrid, row, col, (ro+1), col);
                    return;
                }
                
            //move left
            } else if(eatDir == 3) {
                
                //consider edge cases, simulate a toroidal grid
                int c = lookLeft(grid, row, col);
                
                //check if the space to the left has prey
                if (newGrid[row][c-1] == -1){
                    eatPrey(grid, newGrid, row, col, row, (c-1));
                    return;
                }
            }
        }
        
        //if the predator has gone 15 steps or more without eating a prey, it will automatically die
        if (grid[row][col] >= 15) {
            deceasePredator(newGrid, row, col);
            return;
        }
        
        //randomly generate the direction a predator moves (if there is no prey beside) and test if that direction is available
        for(int i=0;i<order.length;i++) {
            moveDir = order[i];
            
            //move up
            if (moveDir == 0){
                
                //consider edge cases, simulate a toroidal grid
                int ro = lookUp(grid, row, col);
                
                //check if the space above the spot of the predator is free
                if (newGrid[ro-1][col] == -2){
                    
                    //if so, move or reproduce (5% chance of reproduction)
                    if (r.nextInt(20) == 0) {
                        reproducePredator(grid, newGrid, row, col, (ro-1), col);
                    } else {
                        movePredator(grid, newGrid, row, col, (ro-1), col);
                    }
                    
                    return;
                }
                
            //move right
            } else if (moveDir == 1) {
                
                //consider edge cases, simulate a toroidal grid
                int c = lookRight(grid, row, col);
                
                
                //check if the space to the right is free
                if (newGrid[row][c+1] == -2){
                    
                    //move or reproduce
                    if (r.nextInt(20) == 0) {
                        reproducePredator(grid, newGrid, row, col, row, (c+1));
                    } else {
                        movePredator(grid, newGrid, row, col, row, (c+1));
                    }
                    
                    return;
                }
                
            //move down
            } else if (moveDir == 2) {
                
                //consider edge cases, simulate a toroidal grid
                int ro = lookDown(grid, row, col);
                
                
                //check if the space below is free
                if (newGrid[ro+1][col] == -2){
                    
                    //move or reproduce
                    if (r.nextInt(20) == 0) {
                        reproducePredator(grid, newGrid, row, col, (ro+1), col);
                    } else {
                        movePredator(grid, newGrid, row, col, (ro+1), col);
                    }
                    
                    return;
                }
                
            //move left
            } else if (moveDir == 3) {
                
                //consider edge cases, simulate a toroidal grid
                int c = lookLeft(grid, row, col);
                
                
                //check if the space to the left is free
                if (newGrid[row][c-1] == -2){
                    
                    //move or reproduce
                    if (r.nextInt(20) == 0) {
                        reproducePredator(grid, newGrid, row, col, row, (c-1));
                    } else {
                        movePredator(grid, newGrid, row, col, row, (c-1));
                    }
                    
                    return;
                }
                
            } //end if
        } //end while
    }
    
    private void movePrey (int[][] newGrid, int row, int col, int newRow, int newCol) {
        newGrid[row][col] = -2;
        
        //move prey to new spot
        newGrid[newRow][newCol] = -1;
    }
    
    private void movePredator (int[][] grid, int[][] newGrid, int row, int col, int newRow, int newCol) {
        newGrid[row][col] = -2;
        
        //move predator to new spot
        newGrid[newRow][newCol] = (grid[row][col] + 1);
    }
    
    private void reproducePrey (int[][] newGrid, int row, int col, int newRow, int newCol) {
        //fill the original grid with a prey
        newGrid[row][col] = -1;

        //generate new prey
        newGrid[newRow][newCol] = -1;
    }
    
    private void reproducePredator (int[][] grid, int[][] newGrid, int row, int col, int newRow, int newCol) {
        //fill the original spot with a predator
        newGrid[row][col] = (grid[row][col] + 1);
        
        //generate new predator
        newGrid[newRow][newCol] = 1;
    }
    
    private void eatPrey (int[][] grid, int[][] newGrid, int predatorRow, int predatorCol, int preyRow, int preyCol) {
        //relocate predator in the spot of the prey
        grid[preyRow][preyCol] = -2;
        newGrid[predatorRow][predatorCol] = -2;
        newGrid[preyRow][preyCol] = 1;
    }
    
    private void deceasePrey (int[][] newGrid, int row, int col) {
        //remove prey from grid
        newGrid[row][col] = -2;
    }
    
    private void deceasePredator (int[][] newGrid, int row, int col) {
        //remove predator from grid
        newGrid[row][col] = -2;
    }
    
    private int lookUp (int[][] grid, int row, int col) {
        if (row == 0) {
            return grid.length;
        } else {
            return row;
        }
    }
    
    private int lookRight(int[][] grid, int row, int col) {
        if (col == (grid[0].length)-1) {
            return -1;
        } else {
            return col;
        }
    }
    
    private int lookDown(int[][] grid, int row, int col) {
        if (row == (grid.length)-1) {
            return -1;
        } else {
            return row;
        }
    }
    
    private int lookLeft(int[][] grid, int row, int col) {
        if (col == 0) {
            return grid[0].length;
        } else {
            return col;
        }
    }
    
    private int[] shuffleArrPrey(){
        int[] arr = {0,1,2,3};
        int[] shuffled = new int[arr.length];
        int a;
        
        //shuffle arrays for prey differently to simulate there being food at the bottom right corner
        //they preys are more likely to get eaten there as well (which simulates the predators' movements towards food)
        if (r.nextInt(2) == 0) {
            
            //will prioritize movement towards the bottom or to the right
            if (r.nextInt(2) == 0) {
                shuffled[0] = 1;
                shuffled[1] = 2;
            } else {
                shuffled[0] = 2;
                shuffled[1] = 1;
            }

            if (r.nextInt(2) == 0) {
                shuffled[2] = 0;
                shuffled[3] = 3;
            } else {
                shuffled[2] = 0;
                shuffled[3] = 3;
            }
            
        //there is also a 50% chance that they prey move randomly to any open spot beside it
        } else {
            for (int i=0;i<arr.length;i++){
                do {
                    a = r.nextInt(arr.length);
                    if (arr[a] != 10) {
                    break;
                    }
                } while (true);

                shuffled[i] = arr[a];
                arr[a] = 10;

            }
        }
                
        return shuffled;
    }
    
    private int[] shuffleArrPredator(){
        int[] arr = {0,1,2,3};
        int[] shuffled = new int[arr.length];
        int a;
        
        //shuffle array
        for (int i=0;i<arr.length;i++){
            do {
                a = r.nextInt(arr.length);
                if (arr[a] != 10) {
                break;
                }
            } while (true);
            
            shuffled[i] = arr[a];
            arr[a] = 10;
            
        }
        return shuffled;
    }
    
}