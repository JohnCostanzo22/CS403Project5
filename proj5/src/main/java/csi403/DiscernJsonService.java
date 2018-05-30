package csi403;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import javax.json.*;

// Extend HttpServlet class
public class DiscernJsonService extends HttpServlet {

  // Standard servlet method 
    public void init() throws ServletException { 
        // Do any required initialization here - likely none
    }
    
    // Standard servlet method - we will handle a POST operation
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        doService(request, response); 
    }

    // Standard Servlet method
    public void destroy() { 
        // Do any required tear-down here, likely nothing.
    }

    // Standard servlet method - we will not respond to GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        // Set response content type and return an error message
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        // We can always create JSON by hand just concating a string.  
        out.println("{ 'message' : 'Use POST!'}");
    }
    
    // Our main worker method
	/* Takes in Json with a list Points that make a polygon
	* Then it calculates how many integer points are within the polygon
	* And returns, as Json, the number of points within the polygon excluding points on the boundary of the polygon
	*/
    private void doService(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
    
		PrintWriter out = response.getWriter();
		//handle any errors in receiving the Json and processing it
		try {
			//get the input
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			//Use a Stringbuffer to append multiple lines of input
			StringBuffer bufferedJson = new StringBuffer();
			String line = null;
			while((line = br.readLine()) != null) {
				bufferedJson.append(line);
			}
			String jsonStr = bufferedJson.toString();
        
			// Create inList object from the Json input
			InList inList = new JsonClassDiscerner().discern(jsonStr); 
			
			//Create a list of friends from the inList
			ArrayList<Friends> list = inList.getInList();
			//initialize a graph
			Graph graph = new Graph();
			
			//for each friend relationship add each name as a node to the graph
			for(Friends friends: list) {
				Node node1;
				Node node2;
				//if the node already exists get the node
				if(graph.contains(friends.getFriend1())) {
					node1 = graph.getNode(friends.getFriend1());
				} else {
					//otherwise create a new node
					node1 = new Node(friends.getFriend1());
				}
				//if the node already exists get the node
				if(graph.contains(friends.getFriend2())) {
					node2 = graph.getNode(friends.getFriend2());
				} else {
					//otherwise create a new node
					node2 = new Node(friends.getFriend2());
				}
				//add friend relationship and add to graph
				//methods wont add any duplicates
				node1.addFriend(node2);
				node2.addFriend(node1);
				graph.addNode(node1);
				graph.addNode(node2);
			}
			
			//get all potential new friends
			for(Node n: graph.getNodeList()) {
				graph.addNewFriends(n);
			}
			
			//Array to hold all the new friends
			ArrayList<Friends> newlist = new ArrayList<Friends>();
			
			//for each node get all potential friends and create Friends objects and add to newList
			for(Node node: graph.getNodeList()) {
				for(Node friend: node.getPotentialFriends()) {
					//Create Friend object
					Friends i = new Friends(node.getName(), friend.getName());
					//Add it to newList if newList doesn't already contain it
					if(!newlist.contains(i)) 
						newlist.add(i);
				}
			}
			
			//Json array for output
			JsonArrayBuilder outArrayBuilder = Json.createArrayBuilder();
			
			//get Each friend in newList and add to a temp array, then add to output array
			for(Friends f: newlist) {
				//create temp array
				JsonArrayBuilder temp = Json.createArrayBuilder();
				//Add both friends
				temp.add(f.getFriend1());
				temp.add(f.getFriend2());
				//add to output array
				outArrayBuilder.add(temp);
			}
			
			// Set response content type to be JSON
			response.setContentType("application/json");
			//send back the outList of friendships
			out.println("{\"outList\": " + outArrayBuilder.build().toString() + "}"); 			
			
		} catch(Exception e) {
			 response.setContentType("application/json");
			//An error occurred (probably bad Json) so send an error message
			out.println("{\"message\":" + "\"Malformed or invalid Json\"" + "}"); 
		}
    }
}

