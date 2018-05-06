package Model;

//CSV file given to us contains data of more than 5000 movies. So to reduce searching complexity we have used 
//AVL tree
public class YearAvlTree {
	YearNode root;

	YearAvlTree() {
		root = null;

	}

	// A recursive method that inserts year
	void insertYear(Year data) {
		root = insertYear(root, data);

	}

	// A recursive method which inserts year at its logical position
	YearNode insertYear(YearNode current, Year data) {
		// Base case whether the AVL tree is empty or the recursive method has
		// reached the leaf node
		if (current == null) {

			current = new YearNode(data);
		}
		// if the AVL tree is not empty now it checks whether to enter the new
		// Node in left subtree or right subtree
		else {

			// if the new node is smaller than current node it will recursively
			// call this inserstMovie method again
			// but this time the argument will be current.left
			if (data.year.compareTo(current.data.year) < 0)
				current.left = insertYear(current.left, data);

			// if the new node is BIGGER than current node it will recursively
			// call this inserstMovie method again
			// but this time the argument will be current.right
			else {
				current.right = insertYear(current.right, data);
			}
		}
		// AVL tree is special type of BST because, AVL is ordered and balanced
		// BST
		// TO balance the tree we will apply following checks

		// If the current node is unbalanced due to the right subtree we will
		// check whether it is the case of single rotation or double rotation
		if (difffact(current) < -1) {
			// if current node is unbalanced due to the right subtree,
			// and balance factor of right subtree is -1 only single left
			// rotation is enough to balance the tree
			if (difffact(current.right) == -1) {
				// left rotation is called
				current = leftrotation(current);
			}

			// if current node is unbalanced due to right subtree and the
			// balance
			// factor of right subtree is 1 double rotation is required to
			// balance the tree
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
			// if current node is unbalanced due to left subtree and the
			// balanced factor of left subtree is 1 only single
			// right rotation is enough to balance the tree
			if (difffact(current.left) == 1) {
				// left rotation is called
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
	int height(YearNode current) {
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
	int difffact(YearNode current) {
		return height(current.left) - height(current.right);
	}

	// Method to left rotate the AVL tree
	YearNode leftrotation(YearNode x) {
		YearNode y = x.right;
		x.right = y.left;
		y.left = x;
		return y;
	}

	// Method to right rotate the subtree
	YearNode rightrotation(YearNode x) {
		YearNode y = x.left;
		x.left = y.right;
		y.right = x;
		return y;
	}

	public void display() {
		Pre(root);
	}

	private void Pre(YearNode n) {
		if (n == null) {
			return;
		}

		System.out.print("Year : " + n.data.year + "		");
		System.out.println();
		Pre(n.left);
		Pre(n.right);
	}

	Year searchYear(String year) {
		return searchYear(root, year);
	}

	Year searchYear(YearNode n, String year) {
		if (n == null) {
			return null;
		} else if (n.data.year.equalsIgnoreCase(year)) {
			return n.data;
		} else if (year.compareTo(n.data.year) <= 0) {
			return searchYear(n.left, year);
		} else
			return searchYear(n.right, year);

	}
}
