package Solver_2x2x2;

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
		/* This will take the input of an int k that will represent the 
		   number of times the cube will be randomized, then the cube will
		   be turned these times randomly*/
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
	
	public void top_cw()
	{
		// This function rotates one of the faces clockwise
		
		//first rotate the actual face
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
	
	public void top_ccw()
	{
		/* rotating counter-clockwise is the same as rotating clockwise 3 
		   times
		 */
		this.top_cw();
		this.top_cw();
		this.top_cw();
	}
	
	// Each of the other faces are rotated similarly to the top face
	public void front_cw()
	{
		
	}
	
	public void front_ccw()
	{
		this.front_cw();
		this.front_cw();
		this.front_cw();
	}
	
	public void right_cw()
	{
		
	}
	
	public void right_ccw()
	{
		this.right_cw();
		this.right_cw();
		this.right_cw();
	}
	
	public void back_cw()
	{
		
	}
	
	public void back_ccw()
	{
		this.back_cw();
		this.back_cw();
		this.back_cw();
	}
	
	public void left_cw()
	{
		
	}
	
	public void left_ccw()
	{
		this.left_cw();
		this.left_cw();
		this.left_cw();
	}
	
	public void print_cube()
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
		cube.top_cw();
		cube.print_cube();
	}
}
