package Model;

public class Actor {
	public String name;
	public String fblikes;
	public MovieList moviesDone;
//
	public Actor(String name, String fblikes) {

		this.name = name;
		this.fblikes = fblikes;
		moviesDone = null;
	}

	public Actor() {
		// TODO Auto-generated constructor stub
	}

	void insertMovie(Movie node) {
		if (moviesDone == null) {
			moviesDone = new MovieList();
			moviesDone.insert(node);
		} else {
			moviesDone.insert(node);
		}
	}
	
	//A method which prints actor details .Along with All His Movie
	String printActedMovieWithDetail() {
		return "Actor name:" + name + "\t\t" + "\nFb likes :" + fblikes + "\n\n----Movies Acted----\n" + moviesDone.GuiString() + "\n";
	}
	
	//This method generates a coactor list at runtime which generation complexity is 3n 
	//And printing is n
	public String printCoActors() {
		MovieNode temp = moviesDone.root;
		ActorList a = new ActorList();
		do {

			ActorNode tempo = temp.data.actors.root;
			while (tempo != null) {
				if(this!=tempo.data)
					a.insert(tempo.data);
				tempo = tempo.next;
			}
			temp = temp.next;
		} while (temp != null);
		return a.print();

	}
	

	//This Method first search for movie in the movie list
	//If movie is present is return the the actorlist of that specific movie
	public String printCoActorInMovie(String name) {
		if (moviesDone.search(name) != null) {
			Movie temp = moviesDone.search(name);
			ActorList a = temp.actors;
			return a.print();
		} else {
			return "Actor Not Done This Movie";
		}
	}
	
	public MovieList getMoviesDone() {
		return moviesDone;
	}


}


