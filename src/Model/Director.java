package Model;

public class Director {
	public String name;
	public String fblikes;
	public MovieList moviesDirected;

	public Director(String name, String fblikes) {
		super();
		this.name = name;
		this.fblikes = fblikes;
		moviesDirected = new MovieList();
	}

	public Director() {
		// TODO Auto-generated constructor stub
	}
	
	void insertMovie(Movie node) {
		if (moviesDirected == null) {
			moviesDirected = new MovieList();
			moviesDirected.insert(node);
		} else {
			moviesDirected.insert(node);
		}
	}
	
	//Return the String of Director with movie details
	String printDetails() {
		return "Name: " + name + "\nFbLikes : " + fblikes + "\n-----------MoviesDirected---------\n"
				+ moviesDirected.GuiString();
	}

}
