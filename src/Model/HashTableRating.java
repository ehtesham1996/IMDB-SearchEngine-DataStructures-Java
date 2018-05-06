package Model;

public class HashTableRating {
	public static final int length = 12;
	MovieAvlTree[] movietree;

	HashTableRating() {
		movietree = new MovieAvlTree[length];
	}

	void insert(Movie current) {
		if (movietree[HashFunction(current.imdb_score)] == null)
			movietree[HashFunction(current.imdb_score)] = new MovieAvlTree();
		movietree[HashFunction(current.imdb_score)].insertMovie(current);
	}

	static int HashFunction(String string) {
		int index = 11;

		if (!string.isEmpty() && isFloat(string)) {

			int x = (int) Float.parseFloat(string);
			switch (x) {
			case 0:
				index = 0;
				break;
			case 1:
				index = 1;
				break;
			case 2:
				index = 2;
				break;
			case 3:
				index = 3;
				break;
			case 4:
				index = 4;
				break;
			case 5:
				index = 5;
				break;
			case 6:
				index = 6;
				break;
			case 7:
				index = 7;
				break;
			case 8:
				index = 8;
				break;
			case 9:
				index = 9;
				break;
			case 10:
				index = 10;
				break;
			default:
				index = 11;

			}
		}
		return index;
	}

	public void printall() {
		for (int i = 0; i < 27; i++) {
			System.out.println("above " + i);
			movietree[i].display();
		}
	}
	
	//A method that print form Given score to end index 10
	String search(String score) {
		String gui = "";
		if (isFloat(score)) {
			for (int i = Integer.parseInt(score); i < length - 1; i++) {
				if (movietree[i] != null) {

					gui = gui + movietree[i].GetGuiString();
				}
			}
		} else if (movietree[11] != null) {

			gui = gui + movietree[11].GetGuiString();
		}
		return gui;
	}

	public static boolean isFloat(String s) {
		try {
			Float.parseFloat(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}

		return true;
	}

}
