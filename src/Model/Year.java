package Model;

public class Year {
	String year;
	MovieList movies; //We used movie list because at the moment we need to print only

	public Year(String year) {
		this.year = year;
		movies = new MovieList();
	}

}
