package Solver_2x2;
public class Cube_2x2
{
	// colors are represented by integers, 1-6
	int [][] cube;
	
	public Cube_2x2()
	{
		this.reset();
	}
	
	public void reset()
	{
		// conceptual order: top, front, right, back, left, bottom
		// 4 colors are top left, top right, bottom left, bottom right
		cube = new int [] []   {{1, 1, 1, 1}, 
								{2, 2, 2, 2},
								{3, 3, 3, 3},
								{4, 4, 4, 4},
								{5, 5, 5, 5},
								{6, 6, 6, 6}};
								
		// testing stuff
		cube[0][1] = 2;
		cube[0][2] = 3;
		cube[0][3] = 4;
	}
	
	public void mix()
	{
		
	}
	
	public void top_cw()
	{
		int topTopLeft = cube[0][0];
		int topTopRight = cube[0][1];
		int topBotLeft = cube[0][2];
		int topBotRight = cube[0][3];
		
		int frontTopLeft = cube[1][0];
		int frontTopRight = cube[1][1];
		int rightTopLeft = cube[2][0];
		int rightTopRight = cube[2][1];
		int backTopLeft = cube[3][0];
		int backTopRight = cube[3][1];
		int leftTopLeft = cube[4][0];
		int leftTopRight = cube[4][1];
		
		cube[0][0] = topBotLeft;
		cube[0][1] = topTopLeft;
		cube[0][2] = topBotRight;
		cube[0][3] = topTopRight;
		
	}
	
	public void top_ccw()
	{
		this.top_cw();
		this.top_cw();
		this.top_cw();
	}
	
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
		Cube_2x2 cube = new Cube_2x2();
		cube.top_cw();
		cube.print_cube();
	}
}
