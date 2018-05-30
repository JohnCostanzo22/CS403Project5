package csi403;

//Class that is used mostly for recognizing Json
//Contains two people (names) who are friends
public class Friends {

	private String friends[] = new String[2];
	
	//Default Constructor for Json
	public Friends() {
		
	}
	
	//Constructor that sets both friends
	public Friends(String name1, String name2) {
		friends[0] = name1;
		friends[1] = name2;
	}
	
	//Mutator for both friends
	public void setFriends(String friends[]) {
		if(friends.length == 2) {
			this.friends[0] = friends[0];
			this.friends[1] = friends[1];
		}
	}
	
	//Accessor
	public String[] getFriends() {
		return friends;
	}
	
	//Accessor
	public String getFriend1() {
		return friends[0];
	}
	
	//Accessor
	public String getFriend2() {
		return friends[1];
	}
	
	//Compares this object to another object (must be another friend)
	//true only if both friends are the same
	public boolean equals(Object other) {
		//handle errors for non friends
		try {
			String otherArray[] = ((Friends) other).getFriends();
			//compare friend 0 to 0
			if(friends[0].compareTo(otherArray[0]) == 0) {
				//compare friends 1 to 1
				if(friends[1].compareTo(otherArray[1]) == 0)
					return true;
			}
			//compare friends 1 to 0
			else if(friends[1].compareTo(otherArray[0]) == 0) {
				//compare friends 0 to 1
				if(friends[0].compareTo(otherArray[1]) == 0)
					return true;
			}
		} catch(Exception e) {
			
		}
		return false;
	}
}
