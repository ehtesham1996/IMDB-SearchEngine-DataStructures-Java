package Model;


public class Movie {
	public String color;
	public String num_critic_for_reviews;
	public String duration;
	public String gross;
	public String title;
	public String numVotes;
	public String casttotal;
	public String facenumberinposter;
	public String url;
	public String num_user_of_reviews;
	public String language;
	public String country;
	public String contentRating;
	public String budget;
	public Year titleyear;
	public String plotkeywords;

	public String getTitleyear() {
		return titleyear.year;
	}

	public void setTitleyear(Year titleyear) {
		this.titleyear = titleyear;
	}

	public String imdb_score;
	public String aspect_ratio;
	public String facebookLikes;

	public ActorList actors;
	public Director director;
	public GenreList genre;

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public ActorList getActors() {
		return actors;
	}

	public void setActors(ActorList actors) {
		this.actors = actors;
	}

	public GenreList getGenre() {
		return genre;
	}

	public void setGenre(GenreList genre) {
		this.genre = genre;
	}

	public Movie(String color, String num_critic_for_reviews, String duration, String gross, String title,
			String numVotes, String casttotal, String facenumberinposter, String url, String num_user_of_reviews,
			String language, String country, String contentRating, String budget, String imdb_score,
			String aspect_ratio, String facebookLikes,String plotkeywords) {
		super();
		this.color = color;
		this.num_critic_for_reviews = num_critic_for_reviews;
		this.duration = duration;
		this.gross = gross;
		this.title = title;
		this.numVotes = numVotes;
		this.casttotal = casttotal;
		this.facenumberinposter = facenumberinposter;
		this.url = url;
		this.num_user_of_reviews = num_user_of_reviews;
		this.language = language;
		this.country = country;
		this.contentRating = contentRating;
		this.budget = budget;
		this.plotkeywords=plotkeywords;
		this.imdb_score = imdb_score;
		this.aspect_ratio = aspect_ratio;
		this.facebookLikes = facebookLikes;
		
	}

	String Display() {
		return "Title : " + title + "\n" + "\tCountry : " + country + "\tLanguage : " + language + "\tImdb Score :"
				+ imdb_score + "\tYear :" + titleyear.year + "\n\tActors\n " + actors.print() + "\nDirector is :"
				+ director.name + "\n";
	}

}
