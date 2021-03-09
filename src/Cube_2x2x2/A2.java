package Cube_2x2x2;

import java.util.PriorityQueue; 

public class A2
{
	private Node root;
	private int exploredNodes; // just here for testing
	
	public A2(Cube_2x2x2 startState)
	{
		root = new Node(startState, null, "");
		exploredNodes = 0;
	}
	
	public Node createNode(Cube_2x2x2 state)
	{
		return new Node(state,null,null);
	}
	
	// DON'T USE THIS VERSION IT RUNS OUT OF MEMORY AND DOESN'T COLLECT DATA.
	// THERE'S A RECURSIVE ONE BELOW IT.
	public Node IDAStar()
	{
		// Non-recursive version, uses a priority queue.
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		int threshold = (int) (root.h); // this equals root.f, fyi
		
		// this outer loop is present so that the threshold can be increased over as many iterations as required
		while(true)
		{
			// search for a solution with f < threshold
			frontier.add(root);
			while(!frontier.isEmpty())
			{
				Node n = frontier.poll();
				if(n.isSolved())
				{
					// Once a solution is found, print out that solution and end the search
					System.out.println("Solution Path:");
					n.printPath();
					
					return n;
				}
				// when exploring a node that isn't the solution, 
				// expand that node's children and add them to the frontier
				Node [] children = n.generateChildren();
				for(Node child: children)
					if(child.f < threshold)
						frontier.add(child);
			}
		}	
	}
	
	public Node IDAStar_R()
	{
		// Recursive version, much less space is used
		int threshold = (int) (root.h);
		Node result = null;
		while(result == null)
		{
			
			result = IDAStar_R_Helper(root, threshold);
			threshold++;
		}
		return result;
	}
	
	private Node IDAStar_R_Helper(Node node, int threshold)
	{
		exploredNodes = exploredNodes + 1;
		if(node.isSolved())
			return node;
		
		Node [] children = node.generateChildren();
		for(Node child: children)
		{
			if(child.f <= threshold)
			{
				Node result = IDAStar_R_Helper(child, threshold);
				if(result != null)
				{
					return result;
				}
			}
		}
		return null;
	}
	
	// private Node class to manage and create new nodes for states
	private class Node implements Comparable<Node>
	{
		public Node parent;
		public String turn;
		public Cube_2x2x2 state;
		public double h, g, f;
		
		public Node(Cube_2x2x2 state, Node parent, String turn)
		{
			this.state = state;
			this.parent = parent;
			this.turn = turn;
			
			// set h, g, and f
			setH();
			
			if(parent == null)
				g = 0;
			else
				g = parent.g + 1;
			
			f = h + g;
		}
		
		public Node[] generateChildren()
		{
			// returns an array of the children
			// we're locking the top, front, right cubelet in place, so don't rotate those three faces
			int childrenLength = 5, i = 0;
			if(turn.equals(""))
				childrenLength = 6;
			Node [] children = new Node [childrenLength];
			
			Node n;
			Cube_2x2x2 childState;
			if( !turn.equals("bottomCCW"))
			{
				childState = state.clone();
				childState.down(1);
				children[i] = new Node(childState, this, "bottomCW");
				i++;
			}
			if( !turn.equals("bottomCW"))
			{
				childState = state.clone();
				childState.down(3);
				children[i] = new Node(childState, this, "bottomCCW");
				i++;
			}
			if( !turn.equals("backCCW"))
			{
				childState = state.clone();
				childState.back(1);
				children[i] = new Node(childState, this, "backCW");
				i++;
			}
			if( !turn.equals("backCW"))
			{
				childState = state.clone();
				childState.back(3);
				children[i] = new Node(childState, this, "backCCW");
				i++;
			}
			if( !turn.equals("leftCCW"))
			{
				childState = state.clone();
				childState.left(1);
				children[i] = new Node(childState, this, "leftCW");
				i++;
			}
			if( !turn.equals("leftCW"))
			{
				childState = state.clone();
				childState.left(3);
				children[i] = new Node(childState, this, "leftCCW");
				i++;
			}
			return children;
		}
		
		/*heuristic function (Hamming Distance): Giving a fixed corner in the
		  cube and assuming all 3 squares on this corner are in place, we 
		  will count the number of out of place squares on these sides. The 
		  number of these square will be divided by 8 since this is how many
		  squares we move every 1 move. This heuristic is admissible
		 */
		public void setH()
		{
			// wip
			// pick a corner to set
			int topColor = state.cube[0][3];
			int frontColor = state.cube[1][1];
			int rightColor = state.cube[2][0];
			int backColor = solvedOppositeColor(frontColor);
			int leftColor = solvedOppositeColor(rightColor);
			int bottomColor = solvedOppositeColor(topColor);
			
			int[] orientedColors = new int[] {topColor, frontColor, 
					rightColor, backColor, leftColor, bottomColor};
			
			// count out of place squares
			for(int faceIndex = 0; faceIndex < 6; faceIndex++)
			{
				for(int squareColor: state.cube[faceIndex])
				{
					if(squareColor != orientedColors[faceIndex])
					{
						h++;
					}
				}
			}
			
			// sum and divide by 8
			h = h / 8;
		}
		
		private int solvedOppositeColor(int color)
		{
			// in a solved cube, white is always on the opposite face as yellow,
			// red as orange, etc
			
			// white 1, red 2, blue 3, orange 4, green 5, yellow 6
			// top,		front,	right,	back,	left,		bottom
			switch(color)
			{
				case 1:
					return 6;
				case 2:
					return 4;
				case 3:
					return 5;
				case 4:
					return 2;
				case 5:
					return 3;
				case 6:
					return 1;
			}
			return -1;
		}
		
		public boolean isSolved()
		{
			return state.isSolved();
		}

		@Override
		public int compareTo(Node n)
		{
			return (int) (this.f - n.f);
		}
		
		public void printPath()
		{
			if(parent != null)
				parent.printPath();
			System.out.println(turn);
		}
	}
	
	protected static void testHeuristic()
	{
		A2 s = new A2(new Cube_2x2x2());
		for(int k = 1; k < 20; k++)
		{
			for(int i = 0; i < 1000; i++)
			{
				Cube_2x2x2 cube = new Cube_2x2x2();
				cube.randomize(k);
				Node n = s.createNode(cube);
				System.out.println(n.h);
				System.out.println(k);
				System.out.println();
				if(n.h > k)
				{
					System.out.println("ERROR: Either the Heuristic or the Heuristic test is wrong");
					return;
				}
				if(n.h > 2)
				{
					System.out.println("This never happens, there are a maximum of 21 squares that can be wrong, 21/8 < 3");
					return;
				}
			}
		}
	}
	
	private static void testIDAStarR()
	{
		int maxTurns = 20;
		int solvesPerIteration = 10;
		
		// where to record data
		long [][] timeData = new long [maxTurns][solvesPerIteration];
		int [][] exploredData = new int [maxTurns][solvesPerIteration];
		int [][] pathData = new int [maxTurns][solvesPerIteration];
		Node [][] solutionData = new Node [maxTurns][solvesPerIteration];
		
		for(int k = 0; k < maxTurns; k++)
		{
			for(int j = 0; j < solvesPerIteration; j++)
			{
				Cube_2x2x2 state = new Cube_2x2x2();
				state.randomize(k);
				A2 s = new A2(state);
				
				long start = System.currentTimeMillis();
				Node node = s.IDAStar_R();
				long stop = System.currentTimeMillis();
				
				timeData[k][j] = stop - start;
				pathData[k][j] = (int) node.g;
				exploredData[k][j] = s.exploredNodes;
				solutionData[k][j] = node;
			}
		}
		
		
		System.out.println("Number of turns in randomization is the row index, 0 - " + String.valueOf(maxTurns) +
				", with " + String.valueOf(solvesPerIteration) + " test cases each.\n");
		System.out.println("Milliseconds needed to calculate solution.");
		printLongMatrix(timeData);
		System.out.println("\nNumber of turns in each solution.");
		printIntMatrix(pathData);
		System.out.println("\nNumber of nodes explored.");
		printIntMatrix(exploredData);
		System.out.println("\nAverage time needed per turns in randomization.");
		calcAverage(timeData);
		System.out.println("\nAverage path per number of turns.");
		calcAverage(pathData);
		System.out.println("\nAverage nodes explored per number of turns.");
		calcAverage(exploredData);
	}
	
	// methods to print a matrix and to find and print the average of each row.
	public static double [] calcAverage(int [][] matrix)
	{
		double [] result = new double [matrix.length];
	    for (int row = 0; row < matrix.length; row++) {
	    	int sum = 0;
	    	double res = 0;
	        for (int col = 0; col < matrix[row].length; col++) {
	            sum+=matrix[row][col];
	        }
	        result[row] = (double)(sum) / matrix[0].length;
	    }
	    for(double res: result)
	    	System.out.print(Double.valueOf(res) + " ");
	    System.out.println();
	    return result;
	}
	
	public static double [] calcAverage(long [][] matrix)
	{
		double [] result = new double [matrix.length];
	    for (int row = 0; row < matrix.length; row++) {
	    	int sum = 0;
	    	double res = 0;
	        for (int col = 0; col < matrix[row].length; col++) {
	            sum+=matrix[row][col];
	        }
	        result[row] = (double)(sum) / matrix[0].length;
	    }
	    for(double res: result)
	    	System.out.print(Double.valueOf(res) + " ");
	    System.out.println();
	    return result;
	}
	
	public static void printIntMatrix(int[][] matrix) {
		System.out.println();
	    for (int row = 0; row < matrix.length; row++) {
	        for (int col = 0; col < matrix[row].length; col++) {
	            System.out.printf("%4d", matrix[row][col]);
	        }
	        System.out.println();
	    }
	}
	
	public static void printLongMatrix(long[][] matrix) {
		System.out.println();
	    for (int row = 0; row < matrix.length; row++) {
	        for (int col = 0; col < matrix[row].length; col++) {
	            System.out.printf("%4d", matrix[row][col]);
	        }
	        System.out.println();
	    }
	}
	
	public static void main(String [] args)
	{
		A2 s = new A2(new Cube_2x2x2());
		s.testIDAStarR();
		System.out.println("done"); // it takes a bit so it's nice to know that it actually finished
		
	}
}
