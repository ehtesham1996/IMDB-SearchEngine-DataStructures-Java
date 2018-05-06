package Model;

public class ActorNode {
	public Actor data;
	public ActorNode next;

	public ActorNode(Actor root) {

		this.data = root;
		this.next = null;
	}

}
