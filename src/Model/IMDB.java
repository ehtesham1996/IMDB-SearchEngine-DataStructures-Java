package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IMDB {

	HashTableMovie movietree = new HashTableMovie();
	public HashTableActor Actor_tree = new HashTableActor();
	HashTableDirector directortree = new HashTableDirector();
	GenreAvlTree genrelist = new GenreAvlTree();
	HashTableRating rating = new HashTableRating();
	YearAvlTree year = new YearAvlTree();

	public void read() {
		String csvFile = "resources\\IMDB.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				
				String[] column = line.split(cvsSplitBy);

				GenreList glist = new GenreList();
				ActorList actorss = new ActorList();
				if (movietree.search(column[11].replaceAll(" ", "")) == null) {
					Movie node = new Movie(column[0], column[2], column[3], column[8], column[11].replaceAll(" ", ""), column[12],
							column[13], column[15], column[17], column[18], column[19], column[20], column[21], column[22], column[25], column[26],
							column[27],column[16]);
					Director d;
					Year y;

					if (Actor_tree.search(column[10]) != null) {
						Actor tempActor = Actor_tree.search(column[10]);
						actorss.insert(tempActor);
						tempActor.insertMovie(node);
					} else {
						Actor tempActor = new Actor(column[10], column[7]);

						tempActor.insertMovie(node);
						Actor_tree.insert(tempActor);
						actorss.insert(tempActor);
					}
					if (Actor_tree.search(column[6]) != null) {
						Actor tempActor = Actor_tree.search(column[6]);
						actorss.insert(tempActor);
						tempActor.insertMovie(node);
					} else {
						Actor tempActor = new Actor(column[6], column[24]);
						tempActor.insertMovie(node);
						Actor_tree.insert(tempActor);
						actorss.insert(tempActor);
					}
					if (Actor_tree.search(column[14]) != null) {
						Actor tempActor = Actor_tree.search(column[14]);
						actorss.insert(tempActor);
						tempActor.insertMovie(node);
					} else {
						Actor tempActor = new Actor(column[14], column[5]);
						tempActor.insertMovie(node);
						Actor_tree.insert(tempActor);
						actorss.insert(tempActor);
					}
					if (directortree.search(column[1]) != null) {
						d = directortree.search(column[1]);
						d.moviesDirected.insert(node);
					} else {
						d = new Director(column[1], column[4]);
						d.moviesDirected.insert(node);
						directortree.insert(d);
					}
					char[] c = column[9].toCharArray();
					String gp = "";
					for (int i = 0; i < c.length; i++) {

						if (c[i] == '|') {

							if (genrelist.search(gp) != null) {
								Genre tempgen = genrelist.search(gp);
								tempgen.movies.insert(node);
								glist.insert(tempgen);
							} else {
								Genre tempgen = new Genre(gp);
								genrelist.insertGenre(tempgen);
								tempgen.movies.insert(node);
								glist.insert(tempgen);
							}
							gp = "";
						} else {
							gp = gp + c[i];

						}
					}
					if (genrelist.search(gp) != null) {
						Genre tempgen = genrelist.search(gp);
						tempgen.movies.insert(node);
						glist.insert(tempgen);
					} else {
						Genre tempgen = new Genre(gp);
						genrelist.insertGenre(tempgen);
						tempgen.movies.insert(node);
						glist.insert(tempgen);
					}

					if (year.searchYear(column[23]) != null) {
						y = year.searchYear(column[23]);
						y.movies.insert(node);
					} else {
						y = new Year(column[23]);
						y.movies.insert(node);
						year.insertYear(y);
					}
					node.setGenre(glist);
					node.setDirector(d);
					node.setActors(actorss);
					node.setTitleyear(y);
					rating.insert(node);
					movietree.insert(node);

				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String searchMovieByTitle(String name) {
		if (movietree.search(name) == null) {
			return "";
		} else {
			return movietree.search(name).Display();
		}
	}

	public String searchMovieByActor(String name) {
		if (Actor_tree.search(name) == null) {
			return "";
		} else {
			return Actor_tree.search(name).moviesDone.GuiString();
		}

	}

	public String searchMovieByDirector(String name) {
		if (directortree.search(name) == null) {
			return "";
		} else {
			return directortree.search(name).moviesDirected.GuiString();
		}

	}

	public String searchMovieByRating(String rate) {
		return rating.search(rate);
	}

	public String searchMovieByYear(String y) {
		if (year.searchYear(y) == null) {
			return "";
		} else {
			return year.searchYear(y).movies.GuiString();
		}

	}

	public String searchMovieByGenre(String genre) {
		if (genrelist.search(genre) == null) {
			return "";
		} else {
			return genrelist.search(genre).movies.GuiString();
		}
	}

	public String searchActorByName(String name) {
		if (Actor_tree.search(name) == null) {
			return "";
		} else {
			return Actor_tree.search(name).printActedMovieWithDetail();
		}

	}

	public String searchDirectorByName(String name) {
		if (directortree.search(name) == null) {
			return "";
		} else {
			return directortree.search(name).printDetails();
		}

	}
}
