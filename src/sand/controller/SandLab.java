package sand.controller;
import java.awt.*;
import java.util.*;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int RANDOM = 4;
  
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[5];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[RANDOM] = "random";
    
    //1. Add code to initialize the data member grid with same dimensions
    grid = new int[numRows][numCols];
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
	  
    //2. Assign the values associated with the parameters to the grid
	grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
   //Hint - use a nested for loop
	  
	  for(int r = 0; r < grid.length; r++)
	  {
		  for(int c = 0; c < grid[0].length; c++)
		  {
			  Color drawingColor = Color.pink;
			  
			  if(grid[r][c] == SAND)
			  {
				  drawingColor = Color.yellow;
			  }
			  else if(grid[r][c] == METAL)
			  {
				  drawingColor = Color.GRAY;
				  
			  }
			  else if(grid[r][c] == EMPTY)
			  {
				  drawingColor = Color.BLACK;
			  }
			  else if(grid[r][c] == RANDOM)
			  {
				  drawingColor = Color.GREEN;
			  }
			  else if(grid[r][c] == WATER)
			  {
				  drawingColor = Color.BLUE;
			  }
			  
			  display.setColor(r,c,drawingColor);
		  }
	  }
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
	//int scalar = 100;
    int someRandomRow = (int) (Math.random() * (grid.length -1));
    int someRandomCol = (int) (Math.random() * (grid[0].length -1));
    
    int value;
    int left;
    int right;
    int spot = grid[someRandomRow][someRandomCol];
    int below = grid[someRandomRow + 1][someRandomCol];
    
    //remember that you need to watch for the edges of the array
    
    if(spot == SAND && below == EMPTY)
    {
    		value = spot;
    		
    		grid[someRandomRow + 1][someRandomCol] = value;
    		grid[someRandomRow][someRandomCol] = EMPTY;
    }
    if(spot == WATER)
    {
    		value = spot;
    		
    		if(someRandomCol - 1 < 0)
    		{
    			left = grid[someRandomRow][someRandomCol];
    		}
    		else
    		{
    			left = grid[someRandomRow][someRandomCol-1];
    		}
    		if(someRandomCol + 1  > grid[0].length)
    		{
    			right = grid[someRandomRow][someRandomCol];
    		}
    		else
    		{
    			right = grid[someRandomRow][someRandomCol +1];
    		}
    		
    		if(below == EMPTY)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
        		grid[someRandomRow][someRandomCol] = EMPTY;
    		}
    		else if(left == EMPTY)
    		{
    			grid[someRandomRow][someRandomCol - 1] = value;
        		grid[someRandomRow][someRandomCol] = EMPTY;
    		}
    		else if(right == EMPTY)
    		{
    			grid[someRandomRow][someRandomCol + 1] = value;
        		grid[someRandomRow][someRandomCol] = EMPTY;
    		}
    }
  }
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
