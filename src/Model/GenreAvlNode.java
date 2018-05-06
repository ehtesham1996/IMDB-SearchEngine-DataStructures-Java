package Model;

public class GenreAvlNode {
	Genre data;
	GenreAvlNode right;
	GenreAvlNode left;

	public GenreAvlNode(Genre data) {
		this.data = data;
		this.right = null;
		this.left = null;
	}

}
