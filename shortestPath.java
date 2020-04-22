import java.util.*;
import java.lang.*;
public class shortestPath 
{
   public static int dist[];
   public Set<Integer>settled;
   public PriorityQueue<Node> pq;
   public int V;
   List<List<Node>> adj;
   
   shortestPath(int V)
   {
	   this.V=V;
	   dist=new int[V];
	   settled=new HashSet<Integer>();
	   pq=new PriorityQueue<Node>(V,new Node());
   }
   
   public void dijkatras(List<List<Node>> adj,int source)
   {
	   this.adj=adj;
	   for(int i=0;i<V;i++)
		   dist[i]=Integer.MAX_VALUE;
	   pq.add(new Node(source,0));
	   dist[source]=0;
	   
	   while(settled.size()!=V)
	   {
		    int u=pq.poll().node;
		    settled.add(u);
		    neighbour(u);
	   }
   }
   
   public void neighbour(int u)
   {
	   int edgeDistance=-1;
	   int newDistance=-1;
	   
	   for(int i=0;i<adj.get(u).size();i++)
	   {
		   Node v=adj.get(u).get(i);
		   
		   if(settled.contains(v.node)==false)
		   {
			   edgeDistance=v.cost;
			   newDistance=dist[u]+edgeDistance;
			   if(newDistance < dist[v.node])
			   {
				   dist[v.node]=newDistance;
			   }
			   pq.add(new Node(v.node,dist[v.node]));
		   }
		   
	   }
   }
   
   public static void main(String args[])
   {
	   int V=5;
	   int source=1;
	   List<List<Node>> adj=new ArrayList<List<Node>>(); 
	   for(int i=0;i<V;i++)
	   {
		   adj.add(new ArrayList<Node>());
	   }
	   adj.get(0).add(new Node(1,9));
	   adj.get(0).add(new Node(2,6));
	   adj.get(0).add(new Node(3,5));
	   adj.get(0).add(new Node(4, 3)); 
	   
	   adj.get(1).add(new Node(0,9));
	   adj.get(2).add(new Node(0,6));
	   adj.get(3).add(new Node(0,5));
	   adj.get(4).add(new Node(0, 3));
	   
       adj.get(2).add(new Node(1, 2)); 
       adj.get(2).add(new Node(3, 4));
       
       adj.get(1).add(new Node(2, 2)); 
       adj.get(3).add(new Node(2, 4));
       
       shortestPath path=new shortestPath(V);
       path.dijkatras(adj,source);
       System.out.println("The shorted path from node :"); 
       for (int i = 0; i < shortestPath.dist.length; i++) 
           System.out.println(source + " to " + i + " is "
                              + shortestPath.dist[i]);
   }
}
class Node implements Comparator<Node>
{
	public int node;
	public int cost;
	Node()
	{
		
	}
	Node(int data,int cost)
	{
		node=data;
		this.cost=cost;
	}
	
	public int compare(Node node1,Node node2)
	{
		if(node1.cost>node2.cost)
		    return 1;
		if(node1.cost<node2.cost)
			return -1;
		return 0;
	}
}