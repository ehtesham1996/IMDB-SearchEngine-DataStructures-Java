package Model;

//CSV file given to us contains data of more than 5000 movies. So to reduce searching complexity we have used 
//AVL tree
public class ActorAvlTree {
	ActorAvlNode root;

	ActorAvlTree() {
		root = null;
	}

	// A recursive method that inserts Actor
	void insertActor(Actor data) {
		root = insertActor(root, data);

	}

	// A recursive method which inserts Actor at its logical position
	ActorAvlNode insertActor(ActorAvlNode current, Actor data) {
		// Base case whether the AVL tree is empty or the recursive method has
		// reached the leaf node
		if (current == null) {

			current = new ActorAvlNode(data);
		}
		// if the AVL tree is not empty now it checks whether to enter the new
		// Node in left subtree or right subtree
		else {
			// if the new node is smaller than current node it will recursively
			// call this inserstMovie method again
			// but this time the argument will be current.left
			if (data.name.compareTo(current.node.name) <= 0)
				current.left = insertActor(current.left, data);

			// if the new node is BIGGER than current node it will recursively
			// call this inserstMovie method again
			// but this time the argument will be current.right
			else {
				current.right = insertActor(current.right, data);
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
	int height(ActorAvlNode current) {
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
	int difffact(ActorAvlNode current) {
		return height(current.left) - height(current.right);
	}

	// Method to left rotate the AVL tree
	ActorAvlNode leftrotation(ActorAvlNode x) {
		ActorAvlNode y = x.right;
		x.right = y.left;
		y.left = x;
		return y;
	}

	// Method to right rotate the subtree
	ActorAvlNode rightrotation(ActorAvlNode x) {
		ActorAvlNode y = x.left;
		x.left = y.right;
		y.right = x;
		return y;
	}

	public Actor search(String name) {
		return search(root, name);
	}

	private Actor search(ActorAvlNode r, String name) {

		if (r != null) {
			if (name.equalsIgnoreCase(r.node.name)) {
				return r.node;
			} else if (name.compareTo(r.node.name) < 0) {
				return search(r.left, name);
			} else {
				return search(r.right, name);
			}
		} else
			return null;

	}

	public String display() {
		return Pre(root);
	}

	private String Pre(ActorAvlNode n) {
		String gui = "";
		if (n == null) {
			return "";
		}

		gui = gui + "Name : " + n.node.name + "\n";
		gui = gui + "Fb likes : " + n.node.fblikes + "\n";
		gui = gui + "Movies ";
		gui = gui + "\n" + n.node.moviesDone.GuiString();
		gui = gui + "\n";
		gui = gui + Pre(n.left);
		gui = gui + Pre(n.right);
		return gui;
	}

	void searchMoviesByActorName(String name) {
		Actor a = search(name);
		System.out.println(a.name);
		System.out.println(a.fblikes);
		a.moviesDone.displayMovies();
	}
}
