package ibkrsearchengine;

public class TreeNode {
	
	public String data;
	TreeNode left, right;
	
	public TreeNode(String data) {
		
		this.data = data;
		this.left = null;
		this.right = null;
	}
	 
	public String getData() {
		return this.data;
	}
}
