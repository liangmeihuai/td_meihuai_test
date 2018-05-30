package com.algorithm.redblacktree;

public class RBTreeNode
{
	//nil结点的data为这个常量
	public static final int NULL_DATA = Integer.MIN_VALUE; 
	public static final RBTreeNode NULL_NODE = new RBTreeNode(NULL_DATA, Color.BLACK, null, null, null);
	
	public enum Color
	{
		RED("red"),
		BLACK("black");
		
		private String color;
		private Color(String color)
		{
			this.color = color;
		}
		
		@Override
		public String toString()
		{
			return color;
		}
	}
	
	private int data;
	private Color color;
	private RBTreeNode leftChild;
	private RBTreeNode rightChild;
	private RBTreeNode parent;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public RBTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(RBTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public RBTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(RBTreeNode rightChild) {
		this.rightChild = rightChild;
	}

	public RBTreeNode getParent() {
		return parent;
	}

	public void setParent(RBTreeNode parent) {
		this.parent = parent;
	}

	public RBTreeNode(int data, Color color, RBTreeNode leftChild,
			RBTreeNode rightChild, RBTreeNode parent) {
		this.data = data;
		this.color = color;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parent = parent;
	}
	
	//创建一个结点，左右孩子均为nil结点
	public RBTreeNode(int data)
	{
		RBTreeNode leftChild = createNullNode();
		RBTreeNode rightChild = createNullNode();
		
		this.data = data;
		color = Color.RED;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parent = null;
		this.leftChild.parent = this;
		this.rightChild.parent = this;
	}
	
	//创建nil结点
	public static RBTreeNode createNullNode()
	{
		return new RBTreeNode(NULL_DATA, Color.BLACK, null, null, null);
	}
	
	public void leftRotate()
	{
		RBTreeNode originParent = parent;
		RBTreeNode originRight = rightChild;
		rightChild = rightChild.leftChild;
		originRight.leftChild = this;
		
		if(this == originParent.getLeftChild())
			originParent.setLeftChild(originRight);
		else    originParent.setRightChild(originRight);
		originRight.setParent(originParent);
		parent = originRight;
//		if(!rightChild.isNull())
			rightChild.parent = this;
	}
	
	public void rightRotate()
	{
		RBTreeNode originParent = parent;
		RBTreeNode originLeft = leftChild;
		leftChild = leftChild.rightChild;
		originLeft.rightChild = this;
		
		if(this == originParent.getLeftChild())
			originParent.setLeftChild(originLeft);
		else    originParent.setRightChild(originLeft);
		originLeft.setParent(originParent);
		parent = originLeft;
//		if(!leftChild.isNull())
			leftChild.parent = this;
	}
	
	@Override
	public String toString() {
		return data + " ---> " + color;
	}
	
	public boolean isNull()
	{
		return data == NULL_DATA;
	}
	
}
