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
