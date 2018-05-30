package csi403;

import java.util.ArrayList;

//Keeps a list of nodes and maintains friendships and potentialFriends for the nodes
public class Graph {

	private ArrayList<Node> nodeList = new ArrayList<Node>();
	
	//Adds node if it isn't already in the list
	public void addNode(Node node) {
		if(!this.contains(node.getName())) {
			nodeList.add(node);
		}
	}
	
	//Checks if the list contains the node with the name given
	public boolean contains(String s) {
		boolean contains = false;
		for(Node n: nodeList) {
			if(n.getName().equals(s))
				contains = true;
		}
		return contains;
	}
	
	//Returns the node with the name of the String given
	//returns null if that name doesnt exist in the list
	public Node getNode(String s) {
		Node node = null;
		if(this.contains(s)) {
			for(Node n: nodeList) {
				if(n.getName().equals(s))
					node = n;
			}
		}
		return node;
	}
	
	//Accessor
	public ArrayList<Node> getNodeList() {
		return nodeList;
	}
	
	//Adds all new potential friends for the given node
	//Allows duplicate potentialFriends relationships
	public void addNewFriends(Node node) {
		for(Node friend: node.getFriends()) {
			for(Node friendOfFriend: friend.getFriends()) {
				//if friend of a friend is the given node then skip
				if(!friendOfFriend.equals(node)) {
					node.addPotentialFriend(friendOfFriend);
				}
			}
		}
	}
}
