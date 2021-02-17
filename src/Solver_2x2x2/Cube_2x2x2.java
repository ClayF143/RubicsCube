package Solver_2x2x2;

import java.util.concurrent.ThreadLocalRandom;

public class Cube_2x2x2
{
	
	private int [][] cube;
	
	public Cube_2x2x2()
	{
		this.reset();
	}
	
	public void reset()
	{
		/* This function will take the current object of the cube and will
		   reset it to the solution before it gets randomized again*/
		/* The cube is represented as a 2-D array with colors represented 
		   by integers, 1-6 conceptual order: top, front, right, back, left, bottom
		   4 numbered colors represent the order of top left, top right,
		   bottom left, bottom right */
		/* For example: all the 1s indicate red, the 2s indicate white, etc.*/
		cube = new int [] []   {{1, 1, 1, 1}, 
								{2, 2, 2, 2},
								{3, 3, 3, 3},
								{4, 4, 4, 4},
								{5, 5, 5, 5},
								{6, 6, 6, 6}};
								
		/* testing stuff
		cube[0][1] = 2;
		cube[0][2] = 3;
		cube[0][3] = 4; */
	}
	
	public void randomize(int k)
	{
		// unsolves the cube by turning random faces k times
		// can't test until the the code for the rotations is done
		
		for(int i = 0; i < k; i++)
		{
			// generate a random number between 0 and 6
			int randomInt = ThreadLocalRandom.current().nextInt(1,7);
			// testing
			// System.out.println(randomInt);
			switch(randomInt)
			{
				case 1: this.top(1);
					break;
				case 2: this.front(1);
					break;
				case 3: this.right(1);
					break;
				case 4: this.back(1);
					break;
				case 5: this.left(1);
					break;
				case 6: this.down(1);
					break;
				default: System.out.println("Error - randomize method");
			}
		}
	}
	
	public boolean isSolved()
	{
		/* wip
		will determine if the cube is solved by checking each array
		in the matrix and making sure each square has the same color
		An example of a false output is if any of the arrays have 2
		different integers in it so [1,1,1,2] will return false. It will
		return correct when all faces have the same color so [1,1,1,1],
		[3,3,3,3], [2,2,2,2], etc
		*/
		return false;
	}
	
	public Cube_2x2x2 clone()
	{
		// wip
		// creates a clone of this object and output it
		return null;
	}
	
	public void top(int k)
	{
		// This function rotates the top face clockwise k times
		
		// Any integer works, but 5 turns is the same as 1 turn, 6 as 2 etc
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			// first rotate the actual face
			int topTopLeft = cube[0][0];
			int topTopRight = cube[0][1];
			int topBotLeft = cube[0][2];
			int topBotRight = cube[0][3];
			cube[0][0] = topBotLeft;
			cube[0][1] = topTopLeft;
			cube[0][2] = topBotRight;
			cube[0][3] = topTopRight;
			
			// then rotate the squares sharing an edge with the face
			int frontTopLeft = cube[1][0];
			int frontTopRight = cube[1][1];
			int rightTopLeft = cube[2][0];
			int rightTopRight = cube[2][1];
			int backTopLeft = cube[3][0];
			int backTopRight = cube[3][1];
			int leftTopLeft = cube[4][0];
			int leftTopRight = cube[4][1];
			cube[1][0] = rightTopLeft;
			cube[1][1] = rightTopRight;
			cube[2][0] = backTopLeft;
			cube[2][1] = backTopRight;
			cube[3][0] = leftTopLeft;
			cube[3][1] = leftTopRight;
			cube[4][0] = frontTopLeft;
			cube[4][1] = frontTopRight;
		}
		
	}
	
	// Each of the other faces are rotated similarly to the top face
	public void front(int k)
	{
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			
		}
	}
	
	public void right(int k)
	{
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			
		}
	}
	
	public void back(int k)
	{
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			
		}
	}
	
	public void left(int k)
	{
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			
		}
	}
	
	public void down(int k)
	{
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			
		}
	}
	
	public void print()
	{
		// This function prints every number in the cube
		System.out.println("Printing Cube:\n");
		for(int [] face : cube)
		{
			for(int color : face)
			{
				System.out.print(color);
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args)
	{
		// Stuff for testing
		Cube_2x2x2 cube = new Cube_2x2x2();
		cube.top(1);
		cube.print();
	}
}
