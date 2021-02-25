package Cube_2x2x2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

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
		
		// white 1, red 2, blue 3, orange 4, green 5, yellow 6
		cube = new int [] []   {{1, 1, 1, 1}, 
								{2, 2, 2, 2},
								{3, 3, 3, 3},
								{4, 4, 4, 4},
								{5, 5, 5, 5},
								{6, 6, 6, 6}};
	}
	
	public void setCube(int [][] newCube)
	{
		// This method is mostly just present for testing.
		cube = newCube;
	}
	
	public void randomize(int k)
	{
		// scrambles the cube by turning random faces k times
		for(int i = 0; i < k; i++)
		{
			// generate a random number between 0 and 6
			int randomInt = ThreadLocalRandom.current().nextInt(1,7);
			int turns = 1;  // int turns = ThreadLocalRandom.current().nextInt(1,4);   This would randomize the turn between clockwise, ccw, or 180.
			
			switch(randomInt)
			{
				case 1:
					this.top(turns);
					break;
				case 2:
					this.front(turns);
					break;
				case 3:
					this.right(turns);
					break;
				case 4:
					this.back(turns);
					break;
				case 5:
					this.left(turns);
					break;
				case 6:
					this.down(turns);
					break;
				default: System.out.println("Error - randomize method");
			}
		}
	}
	
	public boolean isSolved()
	{
		/*
		will determine if the cube is solved by checking each array
		in the matrix and making sure each square has the same color
		An example of a false output is if any of the arrays have 2
		different integers in it so [1,1,1,2] will return false. It will
		return correct when all faces have the same color so [1,1,1,1],
		[3,3,3,3], [2,2,2,2], etc
		*/
		for(int [] face : this.cube)
		{
			int color = face[0];
			for(int i = 1; i<face.length; i++)
			{
				if(face[i] != color)
					return false;
			}
		}
		return true;
	}
	
	public Cube_2x2x2 clone()
	{
		// wip
		// creates a clone of this object and outputs it
		
		Cube_2x2x2 clone = new Cube_2x2x2();
		for(int i = 0; i < this.cube.length; i++)
		{
			clone.cube[i] = this.cube[i].clone();
		}
		return clone;
	}
	
	/* NOTE:
	 * If you want to look at any face, it's best to look at it as a 2-d square, top down.
	 * Starting from the top face, if you want to look at the top, shift the front to be where the bottom face is
	 * If you want to look at the bottom, shift the front to where the top face is.
	 * If you want to look at the right, back, or left faces, just rotate horizontally.
	 * This is important, especially with the bottom and back faces, to ensure consistency.
	 */
	
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
			// first rotate the actual face
			int frontTopLeft = cube[1][0];
			int frontTopRight = cube[1][1];
			int frontBotLeft = cube[1][2];
			int frontBotRight = cube[1][3];
			cube[1][0] = frontBotLeft;
			cube[1][1] = frontTopLeft;
			cube[1][2] = frontBotRight;
			cube[1][3] = frontTopRight;
			
			// then rotate the squares sharing an edge with the face
			int topBotLeft = cube[0][2];
			int topBotRight = cube[0][3];
			int rightTopLeft = cube[2][0];
			int rightBotLeft = cube[2][2];
			int botTopLeft = cube[5][0];
			int botTopRight = cube[5][1];
			int leftTopRight = cube[4][1];
			int leftBotRight = cube[4][3];
			cube[0][2] = leftBotRight;
			cube[0][3] = leftTopRight;
			cube[2][0] = topBotLeft;
			cube[2][2] = topBotRight;
			cube[5][0] = rightBotLeft;
			cube[5][1] = rightTopLeft;
			cube[4][1] = botTopLeft;
			cube[4][3] = botTopRight;
			
			
		}
	}
	
	public void right(int k)
	{
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			int rightTopLeft = cube[2][0];
			int rightTopRight = cube[2][1];
			int rightBotLeft = cube[2][2];
			int rightBotRight = cube[2][3];
			cube[2][0] = rightBotLeft;
			cube[2][1] = rightTopLeft;
			cube[2][2] = rightBotRight;
			cube[2][3] = rightTopRight;
			
			int topTopRight = cube[0][1];
			int topBotRight = cube[0][3];
			int frontTopRight = cube[1][1];
			int frontBotRight = cube[1][3];
			int botTopRight = cube[5][1];
			int botBotRight = cube[5][3];
			int backTopLeft = cube[3][0];
			int backBotLeft = cube[3][2];
			cube[0][1] = frontTopRight;
			cube[0][3] = frontBotRight;
			cube[1][1] = botTopRight;
			cube[1][3] = botBotRight;
			cube[5][1] = backBotLeft;
			cube[5][3] = backTopLeft;
			cube[3][0] = topBotRight;
			cube[3][2] = topTopRight;
		}
	}
	
	public void back(int k)
	{
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			int backTopLeft = cube[3][0];
			int backTopRight = cube[3][1];
			int backBotLeft = cube[3][2];
			int backBotRight = cube[3][3];
			cube[3][0] = backBotLeft;
			cube[3][1] = backTopLeft;
			cube[3][2] = backBotRight;
			cube[3][3] = backTopRight;
			
			int topTopLeft = cube[0][0];
			int topTopRight = cube[0][1];
			int rightTopRight = cube[2][1];
			int rightBotRight = cube[2][3];
			int botBotLeft = cube[5][2];
			int botBotRight = cube[5][3];
			int leftTopLeft = cube[4][0];
			int leftBotLeft = cube[4][2];
			cube[0][0] = rightTopRight;
			cube[0][1] = rightBotRight;
			cube[2][1] = botBotRight;
			cube[2][3] = botBotLeft;
			cube[5][2] = leftTopLeft;
			cube[5][3] = leftBotLeft;
			cube[4][0] = topTopRight;
			cube[4][2] = topTopLeft;
		}
	}
	
	public void left(int k)
	{
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			int leftTopLeft = cube[4][0];
			int leftTopRight = cube[4][1];
			int leftBotLeft = cube[4][2];
			int leftBotRight = cube[4][3];
			cube[4][0] = leftBotLeft;
			cube[4][1] = leftTopLeft;
			cube[4][2] = leftBotRight;
			cube[4][3] = leftTopRight;
			
			int topTopLeft = cube[0][0];
			int topBotLeft = cube[0][2];
			int frontTopLeft = cube[1][0];
			int frontBotLeft = cube[1][2];
			int botTopLeft = cube[5][0];
			int botBotLeft = cube[5][2];
			int backTopRight = cube[3][1];
			int backBotRight = cube[3][3];
			cube[0][0] = backBotRight;
			cube[0][2] = backTopRight;
			cube[1][0] = topTopLeft;
			cube[1][2] = topBotLeft;
			cube[5][0] = frontTopLeft;
			cube[5][2] = frontBotLeft;
			cube[3][1] = botBotLeft;
			cube[3][3] = botTopLeft;
			
			
		}
	}
	
	public void down(int k)
	{
		k = k % 4;
		
		for(int i = 0; i < k; i++)
		{
			int botTopLeft = cube[5][0];
			int botTopRight = cube[5][1];
			int botBotLeft = cube[5][2];
			int botBotRight = cube[5][3];
			cube[5][0] = botBotLeft;
			cube[5][1] = botTopLeft;
			cube[5][2] = botBotRight;
			cube[5][3] = botTopRight;
			
			int frontBotLeft = cube[1][2];
			int frontBotRight = cube[1][3];
			int rightBotLeft = cube[2][2];
			int rightBotRight = cube[2][3];
			int backBotLeft = cube[3][2];
			int backBotRight = cube[3][3];
			int leftBotLeft = cube[4][2];
			int leftBotRight = cube[4][3];
			cube[1][2] = leftBotLeft;
			cube[1][3] = leftBotRight;
			cube[2][2] = frontBotLeft;
			cube[2][3] = frontBotRight;
			cube[3][2] = rightBotLeft;
			cube[3][3] = rightBotRight;
			cube[4][2] = backBotLeft;
			cube[4][3] = backBotRight;
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
				System.out.print(String.valueOf(color) +" ");
			}
			System.out.print("\n");
		}
	}
	
	public void printColor()
	{
		// This function prints every color in the cube
		System.out.println("Printing Cube:\n");
		for(int [] face: cube)
		{
			for(int color: face)
			{
				switch(color)
				{
				case 1:
					System.out.print("White  ");
					break;
				case 2:
					System.out.print("Red    ");
					break;
				case 3:
					System.out.print("Blue  ");
					break;
				case 4:
					System.out.print("Orange ");
					break;
				case 5:
					System.out.print("Green   ");
					break;
				case 6:
					System.out.print("Yellow ");
					break;
				}
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args)
	{
		boolean done = false;
		Cube_2x2x2 cube = new Cube_2x2x2();
		while(!done)
		{
			// get the input
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter one of the following keywords.\n"
					+ "'randomize' to randomize\n"
					+ "'front', 'right', 'back', 'left', 'top', or 'down' to rotate that face clockwise\n"
					+ "'print' to print the cube\n"
					+ "and 'solve' if you believe you've solved the cube\n"
					+ "and 'done' to end the program");
			String in = scan.nextLine();
			
			int turns = 0;
			if(! (in.equals("print") || in.equals("solve") || in.equals("done")))
			{
				System.out.println("How many rotations would you like?");
				turns = scan.nextInt();
			}
			
			// use the input
			switch(in)
			{
			case "randomize":
				cube.randomize(turns);
				cube.printColor();
				System.out.println();
				break;
			case "front":
				cube.front(turns);
				break;
			case "right":
				cube.right(turns);
				break;
			case "left":
				cube.left(turns);
				break;
			case "top":
				cube.top(turns);
				break;
			case "down":
				cube.down(turns);
				break;
			case "print":
				cube.printColor();
				System.out.println("\n");
				break;
			case "solve":
				if(cube.isSolved())
					System.out.println("Congratulations, you have solved the cube!");
				else
					System.out.println("Sorry, the cube isn't solved yet");
				break;
			case "done":
				done = true;
				break;
			default:
				System.out.println("Looks like you mispelled something, try again.");
				break;
			}
			System.out.println("");
		}
	}
}





