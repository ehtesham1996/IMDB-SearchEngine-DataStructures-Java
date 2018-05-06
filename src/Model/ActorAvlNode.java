package Model;

public class ActorAvlNode {
	Actor node;
	ActorAvlNode left;
	ActorAvlNode right;

	public ActorAvlNode(Actor data) {
		super();
		this.node = data;
		this.left = null;
		this.right = null;
	}

}
