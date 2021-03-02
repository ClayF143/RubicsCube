package Cube_2x2x2;

import java.util.PriorityQueue;

public class Solver
{
	private PriorityQueue<Node> frontier; // not an array, I haven't looked up syntax for prio queues yet
	private Node root;
	
	public boolean solve()
	{
		System.out.println("Solving Cube, please standby.");
		System.out.println("The last of these numbers is proportional to how easy the solution is.");
		System.out.println("Generally, the harder the solution, the longer the computation time.");
		
		int threshold = root.h;
		boolean unsolved = true;
		
		while(unsolved)
		{
			while(!frontier.isEmpty())
			{
				Node n = frontier.poll();
				
				if(n.isSolved())
				{
					// print path
					
					return true;
				}
				
				Node [] children = n.generateChildren();
				for(Node child: children)
				{
					if(child.f < threshold)
					{
						// add to priority queue
					}
				}
			}
			System.out.println(threshold);
			threshold++;
			
		}
		
		return false;
		
		
	}
	
	private class Node implements Comparable<Node>
	{
		public Node parent;
		public Cube_2x2x2 cube;
		public int h, g, f;
		
		public Node(Cube_2x2x2 cube, Node parent)
		{
			this.cube = cube;
			this.parent = parent;
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
		public void setH()
		{
			h = 0;
		}
		
		public boolean isSolved()
		{
			return cube.isSolved();
		}

		@Override
		public int compareTo(Node n)
		{
			return this.f - n.f;
		}
	}
}
