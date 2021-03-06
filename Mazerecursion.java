//Scan all primitive data types
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Mazerecursion {
   public static int[] findStrt(char[][] maze) {
    
    int[] startPoint = new int[2];
    
    for (int x = 0; x < maze.length; x++) {
      for (int y = 0; y < maze[0].length; y++) {
        
        if (maze[x][y] == ('S')) {
          startPoint[0] = x;
          startPoint[1] = y;
                  
          return startPoint;
        } else {
          startPoint[0] = -1;
        }
      }
    }
    
    return startPoint;
  }
   
  public static boolean findSolu(char[][] curMaze, int x, int y) {
    int counter = 0;
    boolean solved = false;
    if (0 <= x && x < curMaze.length && 0 <= y && y < curMaze[0].length && solved == false) {
      
      if (curMaze[x][y] == 'G') { //if goal found
        solved = true;
            
        for (int i = 0; i < curMaze.length; i++) {
          counter = counter + 1;    
              
          String line = ""; //resets "line" variable
              
          for (int k = 0; k < curMaze[0].length; k++) {
                
            line = line + curMaze[i][k]; 
                        
          }
          System.out.println(line);
        }
        
        return solved;
        
      } else if (curMaze[x][y] == '#') { //when you hit a wall
        return solved;
      } else if (curMaze[x][y] == '+') { //when you've already tried this route
        return solved;
      } else if (curMaze[x][y] == '.' || curMaze[x][y] == 'S') { //when there's an open path
        counter = counter + 1;
        
        if (curMaze[x][y] == 'S') {
          counter = counter + 1;
        } else {
          //marks this as part of the tested path if it's not the starting point
          curMaze[x][y] = '+';
        }
        
        if (findSolu(curMaze, x, y + 1) == true) { //test north/up
          solved = true;
          return solved;
        } else if (findSolu(curMaze, x + 1, y) == true) { //test east/right
          solved = true;
          return solved;
        } else if (findSolu(curMaze, x, y - 1) == true) { //test south/down
          solved = true;
          return solved;
        } else if (findSolu(curMaze, x - 1, y) == true) { //test west/left
          solved = true;
          return solved;
        } else if (solved == false) {
          if (curMaze[x][y] == 'S') {
            counter = counter + 1;
          } else {
            curMaze[x][y] = '.';
            
          } //deletes item from solution path if it isn't the starting position
        }
        return solved;
      }
      
    } else {
      //when out of bounds, do nothing.
    }

    return solved;
  }

  public static void main(String[] args) throws FileNotFoundException { 
    boolean finished = false;
    
    while (finished == false) {
      FileReader filRead = null;
      Scanner inputter = new Scanner(System.in);
      String mazeFile = null;
      boolean choices = false;
    
      System.out.println("Welcome to MazeRunner!!");
    
      while (choices == false) {
        System.out.println("Which maze would you like to be solved? "); //ask the user which maze
        System.out.println("1 is easy \n2 is moderate \n3 is hard"); 
        String another = null;
        another = inputter.next();
        choices = true;
      
        if (another.equals("3")) {
          mazeFile = "Maze3.txt"; 
          System.out.println("OHH, you chose the hardest level ");
        } else if (another.equals("1")) {
          mazeFile = "Maze1.txt";
          System.out.println("Ca c'est bebe facile, but here we go!! ");
        } else if (another.equals("2")) {
          mazeFile = "Maze2.txt";
          System.out.println("Not too hard but not too easy, respectable. ");
        } else {
          System.out.println("\nSorry not available");
          choices = false;
        }
      }


    
      //Mention the rules
      System.out.println("Before we start the Maze Recursion, we are going through ground rules");
      System.out.println("'S' = Starting Point (there can only be one, and it must have one)");
      System.out.println("'G' = Goal/Finish (there can only be one)");
      System.out.println("'#' = Wall. Player cannot pass through this.");
      System.out.println("'.' = Open space. Where the player may walk. \n");        
   
      try {
        
        Scanner scan = new Scanner(mazeFile); 

        BufferedReader buff = null;
        String line = "";
       
        filRead = new FileReader(mazeFile);
        buff = new BufferedReader(filRead);
      
        line = buff.readLine(); //reads first line of maze
        BufferedReader buff2 = new BufferedReader(new FileReader(mazeFile));
        int k = 0;
        while (buff2.readLine() != null) k++; 
      
        buff.close();
        buff2.close();
      
        int i = (int) (line.length());
        System.out.println("The maze is " + i + " units long & " + k + " units wide");
        
        char[][] maze = new char[k][i]; //2d maze array creation
        char[] lineArray = new char[line.length()];

         
               
        //new line reader so it starts at the beginning again  
        BufferedReader buff3 = new BufferedReader(new FileReader(mazeFile)); 

        //these nested loops populate the "maze[][]" array.
        for (int x = 0; x < maze.length; x++) {
          
          line = buff3.readLine(); //selects next line
          lineArray = line.toCharArray(); //turn line into char array
                
          for (int y = 0; y < maze[0].length; y++) {
            maze[x][y] = lineArray[y]; //shoves line array into maze array at row[x]
          }
            
        }
        buff3.close();//close the files

        //now that the maze has been created:
        int[] strt = new int[2];
        strt = findStrt(maze);

                 
        if (strt[0] == -1) { //error handler
          System.out.println("Your maze needs to have a starting point marked by the letter 'S'.");
          System.out.println("Please run the program with a valid maze next time.");
        } else {
          
          //this occurs when everything worked correctly, we have a valid starting position, 
          //Then the maze is ready to be solved.
          System.out.println("Start Position: Maze Array at " + strt[0] + ", " + strt[1]);

                     
          boolean mazeSolved = findSolu(maze, strt[0], strt[1]);
          
          if (mazeSolved) {
            System.out.println("A solution path has been found!");
            //System.out.println("It took " + tries() + " moves");
            System.out.println("\nWould you like try another maze? Y/N");
            String another = null;

             
                         another = inputter.next();
            if ((another.equals("N")) || (another.equals("n"))) {
              finished = true;
              System.out.println("BYE BYE");
            } else if ((another.equals("Y")) || (another.equals("y"))) {
              finished = false;
              System.out.println("");
            } else {
              System.out.println("Your input to if you want to replay is invalid \ntry again");
            }
          } else {
            System.out.println("A solution path was not found :(");
            
          }
        }
          
      } catch (FileNotFoundException e) {
        System.out.println("There seems to be an issue when solving the maze");
      } catch (IOException e) {
        System.out.println("There seems to be an issue when solving the maze");
      }
    }
  }
}
