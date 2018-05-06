package Model;

public class YearNode {
	Year data;
	YearNode right;
	YearNode left;

	public YearNode(Year data) {
		this.data = data;
		right = null;
		left = null;
	}

	public Year getData() {
		return data;
	}

	public void setData(Year data) {
		this.data = data;
	}
}
