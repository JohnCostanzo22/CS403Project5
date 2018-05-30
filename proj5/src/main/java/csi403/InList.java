package csi403;

import java.util.ArrayList;

//Class used only for Json recognition
//ex: {"inList: [{"Todd","Bob"}]}
public class InList {
	private ArrayList<Friends> inList = new ArrayList<Friends>();
	
	//default constructor
	public InList() {
		
	}
	//Mutator
	public void setInList(ArrayList<Friends> list) {
		inList = list;
	}
	//Accessor
	public ArrayList<Friends> getInList() {
		return inList;
	}
}
