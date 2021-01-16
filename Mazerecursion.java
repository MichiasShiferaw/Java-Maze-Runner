//Scan all primitive data types
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner

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
