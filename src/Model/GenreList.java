package Model;

public class GenreList {
	GenreNode root;

	GenreList() {
		root = null;
	}
	
	//A method that add the current genre data at front
	//This method uses search method for duplication
	void insert(Genre data) {
		if (root == null) {
			root = new GenreNode(data);
			root.next = null;
		} else {
			if (search(data.type) == null) {
				GenreNode newnode = new GenreNode(data);
				newnode.next = root;
				root = newnode;
			}

		}

	}
	
	//Prints in ascending order it is used only for checking purposes 
	void print() {
		GenreNode loc = root;
		while (loc != null) {
			System.out.println(loc.data.type);
			loc = loc.next;
		}
	}
	
	//Search method that search a genre by name in method recursively
	Genre search(String type) {
		return search(root, type);
	}

	Genre search(GenreNode current, String type) {
		if (current == null) {
			return null;
		} else if (type.equalsIgnoreCase(current.data.type)) {
			return current.data;
		} else {
			return search(current.next, type);
		}
	}
}
