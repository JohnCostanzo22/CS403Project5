package csi403;

import java.util.LinkedList;

//Class that creates a node of a person for the graph
//Each node will have a list of friends
//and a list of potentialFriends (people 1 friend away from them)
public class Node {
	
	private String name;
	private LinkedList<Node> friendlist;
	private LinkedList<Node> potentialFriends;
	
	//Constructor sets name and initializes friendlist and potentialfriend list
	public Node(String name) {
		this.name = name;
		friendlist = new LinkedList<Node>();
		potentialFriends = new LinkedList<Node>();
	}
	
	//Accessor
	public String getName() {
		return name;
	}
	
	//Adds a friend to FriendList if it isnt already on the list
	public void addFriend(Node node) {
		if(!friendlist.contains(node)) {
			friendlist.add(node);
		}
	}
	
	//Accessor
	public LinkedList<Node> getPotentialFriends() {
		return potentialFriends;
	}
	
	//Adds a person to potentialFriendList if they arent already in the friendList
	//Allows duplicate potentialFriends in Nodes
	public void addPotentialFriend(Node node) {
		if(!friendlist.contains(node))
			potentialFriends.add(node);
	}
	
	//Determines if this node is the same person as the given String
	public boolean equals(String name) {
		return this.name.equals(name);
	}
	
	//Accessor
	public LinkedList<Node> getFriends() {
		return friendlist;
	}
	
	//Creates a toString for node of the name
	public String toString() {
		return name;
	}
}
