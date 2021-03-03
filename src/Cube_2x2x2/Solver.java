package Cube_2x2x2;

import java.util.PriorityQueue;

public class Solver
{
	private Node root;
	
	public Solver(Cube_2x2x2 startState)
	{
		root = new Node(startState, null, null);
	}
	
	public Node createNode(Cube_2x2x2 state)
	{
		return new Node(state,null,null);
	}
	
	public void IDAStar()
	{
		System.out.println("Solving Cube, please standby.");
		System.out.println("The last of these numbers is proportional to how easy the solution is.");
		System.out.println("Generally, the harder the solution, the longer the computation time.");
		
		// this is talking about the threshold that's printed at the bottom of the loop
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		int threshold = root.h; // this equals root.f, fyi
		
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
					return;
				}
				
				// when exploring a node that isn't the solution, 
				// expand that node's children and add them to the frontier
				Node [] children = n.generateChildren();
				for(Node child: children)
					if(child.f < threshold)
						frontier.add(child);
			}
			System.out.println(threshold);
			threshold++;
		}
	}
	
	private class Node implements Comparable<Node>
	{
		public Node parent;
		public String turn;
		public Cube_2x2x2 state;
		public int h, g, f;
		
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
			// wip
			// return an array of the children
			return null;
		}
		
		// heuristic function
		// hamming distance
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
			
			int[] orientedColors = new int[] {topColor, frontColor, rightColor, backColor, leftColor, bottomColor};
			
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
			return this.f - n.f;
		}
		
		public void printPath()
		{
			if(parent != null)
				parent.printPath();
				System.out.println(turn);
		}
	}
	private static void testHeuristic()
	{
		Solver s = new Solver(new Cube_2x2x2());
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
					// never happens <.< this heuristic is a terrible guesser
					System.out.println("Woah");
					System.out.println(n.h);
					return;
				}
			}
		}
	}
	
	public static void main(String [] args)
	{
		testHeuristic();
	}
}
