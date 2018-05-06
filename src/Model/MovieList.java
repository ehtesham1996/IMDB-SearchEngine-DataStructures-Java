package Model;

public class MovieList {
	public MovieNode root;

	MovieList() {
		root = null;
	}
	
	//A method that add the current Movie data at front
	//This method uses search method for duplication
	void insert(Movie data) {
		MovieNode newnode = new MovieNode(data);
		if (root == null) {
			root = newnode;
			root.next = null;
		} else {
			newnode.next = root;
			root = newnode;
		}

	}
	
	public void displayMovies() {
		displayMovies(root);
	}

	void displayMovies(MovieNode r) {
		if (r == null) {
			return;
		} else {
			System.out.print(r.data.title + " , ");
			displayMovies(r.next);
		}
	}
	
	//Return printing string in ascending order it is used only for checking purposes 
	//recursive
	public String GuiString() {
		return GuiString(root);
	}

	public String GuiString(MovieNode r) {
		if (r == null) {
			return "";
		} else {
			String gui = "";
			gui = gui + "Title : " + r.data.title + "\n\t";
			gui = gui + "Country : " + r.data.country + "\t";
			gui = gui + "Language : " + r.data.language + "\t";
			gui = gui + "Imdb Score :" + r.data.imdb_score + "\t";
			gui = gui + "\tYear :" + r.data.titleyear.year + "\n" + GuiString(r.next);
			return gui;
		}
	}

	
	//Search method that search a movie by name in method recursively
	Movie search(String name) {
		return search(root, name);
	}

	Movie search(MovieNode current, String name) {
		if (current == null)
			return null;
		else if (name.equalsIgnoreCase(current.data.title)) {
			return current.data;
		} else {
			return search(current.next, name);
		}
	}

}
