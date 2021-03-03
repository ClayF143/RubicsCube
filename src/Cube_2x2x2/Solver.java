package Cube_2x2x2;

import java.util.PriorityQueue;

public class Solver
{
	private PriorityQueue<Node> frontier;
	private Node root;
	
	public void IDAStar()
	{
		System.out.println("Solving Cube, please standby.");
		// this is talking about the threshold that's printed at the bottom of the loop
		System.out.println("The last of these numbers is proportional to how easy the solution is.");
		System.out.println("Generally, the harder the solution, the longer the computation time.");
		
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
				
				Node [] children = n.generateChildren(); // might need to turn this into a list
				for(Node child: children)
				{
					if(child.f < threshold)
					{
						frontier.add(child);
					}
				}
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
			// return an array of the children
			return null;
		}
		
		// heuristic function
		// hamming distance
		public void setH()
		{
			// wip
			// pick a corner to set
			// count out of place squares
			// sum and divide by 8
			h = 0;
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
}
