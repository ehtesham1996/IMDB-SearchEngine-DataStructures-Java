package Model;

public class ActorList {
	public ActorNode root;

	ActorList() {
		root = null;
	}
	
	//A method that add the current actor data at front
	void insert(Actor data) {
		if (root == null) {
			root = new ActorNode(data);
			root.next = null;
		} else {
			if (!search(data.name)) {
				ActorNode newnode = new ActorNode(data);
				newnode.next = root;
				root = newnode;
			}

		}

	}
	
	//Prints in ascending order 
	String print() {
		return print(root);
	}

	String print(ActorNode current) {
		String gui = "";
		while (current != null) {
			gui = gui + current.data.name + "\n";
			current = current.next;
		}
		return gui;
	}
	
	//Search method that search a actor by name in method recursively
	boolean search(String name) {
		return search(root, name);
	}

	boolean search(ActorNode current, String name) {
		if (current == null) {
			return false;
		} else if (name.equals(current.data.name)) {
			return true;
		} else {
			return search(current.next, name);
		}
	}

}
