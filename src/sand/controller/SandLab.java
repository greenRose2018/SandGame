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
  public static final int GRASS = 4;
  public static final int FIRE = 5;
  public static final int DIRT = 6;
  public static final int OXYGEN = 7;
  public static final int GLASS = 8;
  
  
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
    names = new String[9];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[GRASS] = "Grass seed";
    names[FIRE] = "Fire";
    names[DIRT] = "Dirt";
    names[OXYGEN] = "Oxygen";
    names[GLASS] = "Glass";
    
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
			  Color brown = new Color(102,51,0);
			  
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
			  else if(grid[r][c] == GRASS)
			  {
				  drawingColor = Color.GREEN;
			  }
			  else if(grid[r][c] == WATER)
			  {
				  drawingColor = Color.BLUE;
			  }
			  else if(grid[r][c] == FIRE)
			  {
				  drawingColor = Color.RED;
			  }
			  else if(grid[r][c] == DIRT)
			  {
				  drawingColor = brown;
			  }
			  else if(grid[r][c] == OXYGEN)
			  {
				  drawingColor = Color.lightGray;
			  }
			  else if(grid[r][c] == GLASS)
			  {
				drawingColor = Color.CYAN;
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
    int someRandomRow = (int) (Math.random() * (grid.length -1));
    int someRandomCol = (int) (Math.random() * (grid[0].length -1));
    
    int value;
    int left;
    int right;
    int above;
    int spot = grid[someRandomRow][someRandomCol];
    int below = grid[someRandomRow + 1][someRandomCol];
    
    //remember that you need to watch for the edges of the array
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
    
	if(someRandomRow - 1 < 0)
	{
		above = grid[someRandomRow][someRandomCol];
	}
	else 
	{
		above = grid[someRandomRow -1][someRandomCol];
	}
	
    if(spot == SAND )
    {
    		value = spot;
    		
    		if(below == EMPTY)
    		{
	    		grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = EMPTY;
    		}
    		else if(below == WATER)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = WATER;
    		}
    		else if(below == DIRT)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = DIRT;
    		}
    		else if(below == GRASS)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = EMPTY;
    		}
    		else if(below == FIRE || above == FIRE || left == FIRE || right == FIRE)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = GLASS;
    		}
    }
    if(spot == WATER)
    {
    		value = spot;
    		
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
    		else if(below == FIRE || above == FIRE || left == FIRE || right == FIRE)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = OXYGEN;
    		}
    }
    if(spot == GRASS)
    {
    		value = spot;
    		
    		if(below == EMPTY)
    		{
	    		grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = EMPTY;
    		}
    		else if(above == WATER && below == DIRT)
    		{
    			grid[someRandomRow-1][someRandomCol] = value;
    			grid[someRandomRow][someRandomCol] = GRASS;
    		}
    		else if(below == WATER)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = WATER;
    		}
    }
    if(spot == DIRT)
    {
    		value = spot;
    		if(below == EMPTY)
		{
	    		grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = EMPTY;
		}
    }
    if(spot == FIRE)
    {
    		value = spot;
    		if(below == EMPTY)
    		{
	    		grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = EMPTY;
			}
    		else if( below == GRASS)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = EMPTY;
    		}
    		else if(below == FIRE)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = EMPTY;
    		}
    		else if(below == SAND)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = GLASS;
    		}
    		else if(below == OXYGEN || above == OXYGEN || left == OXYGEN || right == OXYGEN)
    		{
    			grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = EMPTY;
    		}
//    		else if(below == WATER || above == WATER || left == WATER || right == WATER)
//    		{
//    			grid[someRandomRow + 1][someRandomCol] = value;
//	    		grid[someRandomRow][someRandomCol] = OXYGEN;
//    		}
    		
    }
    if(spot == OXYGEN)
    {
    		value = spot;
    		if(above == EMPTY )
		{
    				grid[someRandomRow - 1][someRandomCol] = value;
    				grid[someRandomRow][someRandomCol] = EMPTY;
		}
    }
    if(spot == GLASS)
    {
    		value = spot;
    		if(below == EMPTY)
		{
	    		grid[someRandomRow + 1][someRandomCol] = value;
	    		grid[someRandomRow][someRandomCol] = EMPTY;
		}
    }
    if(spot == DIRT)
    {
    		value = spot;
    		if(below == EMPTY)
		{
	    		grid[someRandomRow + 1][someRandomCol] = value;
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
