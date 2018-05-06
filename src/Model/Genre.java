package Model;

public class Genre {
	String type;
	MovieList movies; //We used movie list because at the moment we need to print only

	public Genre(String type) {
		super();
		this.type = type;
		this.movies = new MovieList();
	}

}
