package Model;

// CSV file given to us contains data of more than 5000 movies. So to reduce searching complexity we have used 
// AVL tree
public class MovieAvlTree {
	MovieAvlNode root;

	MovieAvlTree() {
		root = null;

	}

	// A recursive method that inserts movie
	void insertMovie(Movie data) {
		root = insertMovie(root, data);

	}

	// A recursive method which inserts movie at its logical position
	MovieAvlNode insertMovie(MovieAvlNode current, Movie data) {

		// Base case whether the AVL tree is empty or the recursive method has
		// reached the leaf node
		if (current == null) {

			current = new MovieAvlNode(data);
		}

		// if the AVL tree is not empty now it checks whether to enter the new
		// Node in left subtree or right subtree
		else {

			// if the new node is smaller than current node it will recursively
			// call this inserstMovie method again
			// but this time the argument will be current.left
			if (data.title.compareTo(current.data.title) <= 0)
				current.left = insertMovie(current.left, data);

			// if the new node is BIGGER than current node it will recursively
			// call this inserstMovie method again
			// but this time the argument will be current.right
			else {
				current.right = insertMovie(current.right, data);
			}
		}

		// AVL tree is special type of BST because, AVL is ordered and balanced
		// BST
		// TO balance the tree we will apply following checks

		// If the current node is unbalanced due to the right subtree we will
		// check whether it is the case of single rotation or double rotation
		if (difffact(current) < -1) {

			// if current node is unbalanced due to the right subtree, 
			// and balance factor of right subtree is -1 only single left rotation is enough to balance the tree
			if (difffact(current.right) == -1) {
				// left rotation is called
				current = leftrotation(current);
			}

			// if current node is unbalanced due to  right subtree and the balance 
			//factor of right subtree is 1 double rotation is required to balance the tree
			if (difffact(current.right) == 1) {

				// first right rotation is done to balance the unbalanced left
				// subtree
				current.right = rightrotation(current.right);
				// After right rotation, left rotation is done to balance the
				// tree
				current = leftrotation(current);
			}
		}
		// If the left subtree is unbalanced we will check whether it is the
		// case of single rotation or double rotation
		if (difffact(current) > 1) {

			// if current node is unbalanced due to left subtree and the balanced factor of left subtree is 1 only single
			// right rotation is enough to balance the tree
			if (difffact(current.left) == 1) {
				// right rotation is called
				current = rightrotation(current);
			}
			// if current node is unbalanced due to left subtree and balance
			// factor is -1 double
			// rotation is required to balance the tree
			if (difffact(current.left) == -1) {
				// first left rotation is done to balance the unbalanced left
				// subtree
				current.left = leftrotation(current.left);
				// After left rotation, right rotation is done to balance the
				// tree
				current = rightrotation(current);
			}
		}

		return current;
	}

	// Method to calculate the height of an AVL tree
	int height(MovieAvlNode current) {
		int leftheight;
		int rightheight;
		if (current == null)
			return 0;
		else if (current.right == null && current.left == null) {
			return 1;
		} else {
			leftheight = height(current.left);
			rightheight = height(current.right);
			if (leftheight >= rightheight)
				return 1 + leftheight;
			else
				return 1 + rightheight;
		}
	}

	// Method to check balancing of the AVL tree
	int difffact(MovieAvlNode current) {
		return height(current.left) - height(current.right);
	}

	// Method to left rotate the AVL tree
	MovieAvlNode leftrotation(MovieAvlNode x) {
		MovieAvlNode y = x.right;
		x.right = y.left;
		y.left = x;
		return y;
	}

	// Method to right rotate the subtree
	MovieAvlNode rightrotation(MovieAvlNode x) {
		MovieAvlNode y = x.left;
		x.left = y.right;
		y.right = x;
		return y;
	}

	public void display() {
		Pre(root);
	}

	private void Pre(MovieAvlNode n) {
		if (n == null) {
			return;
		}

		System.out.print("Title : " + n.data.title + "		");
		System.out.print("Country : " + n.data.country + "		");
		System.out.print("Language : " + n.data.language + "		");
		System.out.print("Imdb Score :" + n.data.imdb_score + "       ");
		System.out.print("Year :" + n.data.titleyear);
		System.out.println();
		Pre(n.left);
		Pre(n.right);
	}

	// Method that returns movie after being searched my its title
	Movie searchByTitle(String name) {
		return searchByTitle(root, name);
	}

	// This method is recursively called by the method above
	Movie searchByTitle(MovieAvlNode n, String name) {
		if (n == null) {
			return null;
		} else if (name.compareToIgnoreCase(n.data.title) == 0) {
			return n.data;
		} else if (name.compareTo(n.data.title) < 0) {
			return searchByTitle(n.left, name);
		} else
			return searchByTitle(n.right, name);

	}

	// Method to show movie in the GUI
	public String GetGuiString() {
		return GetGuiString(root);
	}

	private String GetGuiString(MovieAvlNode n) {
		if (n == null) {
			return "";
		}
		String gui = "";
		gui = gui + "Title : " + n.data.title + "\n\t";
		gui = gui + "Country : " + n.data.country + "\t";
		gui = gui + "Language : " + n.data.language + "\t";
		gui = gui + "Imdb Score :" + n.data.imdb_score + "\t";
		gui = gui + "\tYear :" + n.data.titleyear.year + "\n";

		gui = gui + GetGuiString(n.left);
		gui = gui + GetGuiString(n.right);
		return gui;
	}

}
