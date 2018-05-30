package com.algorithm.redblacktree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.algorithm.redblacktree.RBTreeNode.Color;


public class RedBlackTree
{
	//缓存中序遍历的数据
	//若调用了update/delete操作，导致结点变化
	//则在update/delete方法中clear这个List
	protected List<Integer> midOrderVisitList = new ArrayList<Integer>();
	
	//类似于链表中的头指针
	//它的左孩子才是真正的根结点
	//delete操作和rotate操作中，涉及父结点
	//fakeRoot的存在就是为了方便真正根结点的操作
	protected RBTreeNode fakeRoot;
	
	public RedBlackTree(RBTreeNode root)
	{
		this();
		fakeRoot.setLeftChild(root);
		root.setParent(fakeRoot);
		root.setColor(RBTreeNode.Color.BLACK);
	}
	
	public RedBlackTree()
	{
		fakeRoot = new RBTreeNode(-1);
	}
	
	public boolean isEmpty()
	{
		return fakeRoot.getLeftChild().isNull();
	}
	
	public void invalidateVisitList()
	{
		midOrderVisitList.clear();
	}
	
	public RBTreeNode getRoot()
	{
		return fakeRoot.getLeftChild();
	}
	
	public boolean isRed(RBTreeNode node)
	{
		return node.getColor() == RBTreeNode.Color.RED;
	}
	
	/**
	 * 递归查询某个数据是否在树中
	 * @param node	指定的子树
	 * @param data	要查找的数据
	 * @return	结果结点。若有匹配数据项，则返回对应结点。若无匹配，则返回叶子结点。
	 */
	protected RBTreeNode recursiveSearch(RBTreeNode node, int data)
	{
		//子树为空（空结点），即找不到匹配的结点
		//此时parent有三种情况：
		//1、叶结点 2、仅有左子树 3、仅有右子树
		//返回parent是为了方便进行插入操作，因为插入一个结点要先search
		if(node.isNull())
			return node.getParent();
		int _data = node.getData();
		if(_data == data)
			return node;
		//左子树或右子树递归查找
		if(data < _data)
			return recursiveSearch(node.getLeftChild(), data);
		return recursiveSearch(node.getRightChild(), data);
	}
	
	/**
	 * 非递归查询某个数据项是否在树中
	 * @param node	指定的子树
	 * @param data	要查找的数据
	 * @return	结果结点。若有匹配数据项，则返回对应结点。若无匹配，则返回叶子结点。
	 */
	protected RBTreeNode normalSearch(RBTreeNode node, int data)
	{
		RBTreeNode parent = RBTreeNode.NULL_NODE;
		while(!node.isNull())
		{
			int _data = node.getData();
			if(_data == data)
				return node;
			parent = node;
			if(data < _data)
				node = node.getLeftChild();
			else
				node = node.getRightChild();
		}
		return parent;
	}
	
	//对外提供的查询接口
	//具体的实现可使用递归查询，也可不使用递归查询
	public RBTreeNode search(int data)
	{
		if(!isEmpty())
//				return recursiveSearch(getRoot(), data);
			return normalSearch(getRoot(), data);
		return RBTreeNode.NULL_NODE;
	}
	
	protected void midOrderVisit(RBTreeNode tree, List<Integer> dataList)
	{
		if(tree.isNull())
			return;
		midOrderVisit(tree.getLeftChild(), dataList);
		dataList.add(tree.getData());
		midOrderVisit(tree.getRightChild(), dataList);
	}
	
	
	public List<Integer> midOrderVisit()
	{
		//中序缓存列表中有数据，则无须再次遍历，直接返回该列表
		if(midOrderVisitList.size() == 0)
			midOrderVisit(fakeRoot.getLeftChild(), midOrderVisitList);
		return midOrderVisitList;
	}
	
	public boolean insert(int data)
	{
		RBTreeNode resultTree = search(data);
		
		//空树
		if(resultTree.isNull())
		{
			RBTreeNode root = new RBTreeNode(data);
			fakeRoot.setLeftChild(root);
			root.setParent(fakeRoot);
			//红黑树中根结点为黑
			root.setColor(Color.BLACK);
			return true;
		}
		
		//原树中已经有该数据项，不必插入
		int _data = resultTree.getData();
		if(data == _data)    return false;
		
		//非空树
		RBTreeNode newTree = new RBTreeNode(data);
		if(data < _data)
			resultTree.setLeftChild(newTree);
		else    resultTree.setRightChild(newTree);
		newTree.setParent(resultTree);
		
		//调整红黑树使之平衡
		insertRebalance(newTree);
		
		//树结构变化，中序缓存清空
		midOrderVisitList.clear();
		return true;
	}
	
	private void insertRebalance(RBTreeNode node)
	{
		RBTreeNode parent = node.getParent();
		while(parent != fakeRoot && parent.getColor() == Color.RED)
		{
			RBTreeNode grandpa = parent.getParent();
			RBTreeNode uncle;
			//父结点是祖父结点的左孩子
			if(parent == grandpa.getLeftChild())
			{
				uncle = grandpa.getRightChild();
				//case1——叔结点为红
				if(isRed(uncle))
				{
					uncle.setColor(Color.BLACK);
					parent.setColor(Color.BLACK);
					grandpa.setColor(Color.RED);
					node = grandpa;
				}
				//case2——叔结点为黑，且当前结点是其父结点的右孩子
				else if(node == parent.getRightChild())
				{
					node = parent;
					node.leftRotate();
				}
				//case 3——叔结点为黑，且当前结点是其父结点的左孩子
				else if(node == parent.getLeftChild())
				{
					parent.setColor(Color.BLACK);
					grandpa.setColor(Color.RED);
					node = grandpa;
					node.rightRotate();
				}
			}
			//父结点是祖父结点的右孩子
			else
			{
				uncle = grandpa.getLeftChild();
				//case1——叔结点为红
				if(isRed(uncle))
				{
					uncle.setColor(Color.BLACK);
					parent.setColor(Color.BLACK);
					grandpa.setColor(Color.RED);
					node = grandpa;
				}
				//case2——叔结点为黑，且当前结点是其父结点的左孩子
				else if(node == parent.getLeftChild())
				{
					node = parent;
					node.rightRotate();
				}
				//case 3——叔结点为黑，且当前结点是其父结点的右孩子
				else if(node == parent.getRightChild())
				{
					parent.setColor(Color.BLACK);
					grandpa.setColor(Color.RED);
					node = grandpa;
					node.leftRotate();
				}
			}
			parent = node.getParent();
		}
		getRoot().setColor(Color.BLACK);
	}
	
	/**
	 * 红黑树删除结点
	 * @param data	要删除的数据
	 * @return	true——删除成功。false——删除失败
	 */
	public boolean delete(int data)
	{
		//空树
		if(isEmpty())    return false;
		
		RBTreeNode target = search(data);
		//树中不存在该数据，无法删除
		int targetData = target.getData();
		if(targetData != data)    return false;
		
		final RBTreeNode parent = target.getParent();
		final RBTreeNode left = target.getLeftChild();
		final RBTreeNode right = target.getRightChild();
		//保存用于替代被删除结点的那个结点
		RBTreeNode replacement;
		Color originalColor = target.getColor();
		
		//右子树空（包含两种情况：1、左右子树均空  2、仅有左子树）
		if(right.isNull())
		{
			if(target == parent.getLeftChild())
				parent.setLeftChild(left);
			else    parent.setRightChild(left);
			left.setParent(parent);
			replacement = left;
		}
		//左子树空
		else if(left.isNull())
		{
			if(target == parent.getLeftChild())
				parent.setLeftChild(right);
			else    parent.setRightChild(right);
			right.setParent(parent);
			replacement = right;
		}
		//左右子树均非空
		else
		{
			//找到欲删除结点的左子树的右分支尽头的叶结点
			//以之替换欲删除的结点的位置
			RBTreeNode temp = target.getLeftChild();
			while(!temp.getRightChild().isNull())
				temp = temp.getRightChild();
			
			target.setData(temp.getData());
			
			RBTreeNode tempLeftChild = temp.getLeftChild();
			RBTreeNode tempParent = temp.getParent();
			//temp的父结点仍等于target结点
			//说明循环并没有执行，即target的左子树并没有右分支
			if(tempParent == target)
				tempParent.setLeftChild(tempLeftChild);
			else
				tempParent.setRightChild(tempLeftChild);
			tempLeftChild.setParent(tempParent);
			
			//这里用了一个trick，交换temp与target的data
			//最终不会删除target。而是删除temp结点
			//用于替补temp位置的，就是tempLeftChild
			replacement = tempLeftChild;
			//因为删除的是temp，所以originColor要修改成temp的颜色
			originalColor = temp.getColor();
		}
		//树结构变化，中序缓存清空
		midOrderVisitList.clear();
		//被删除的结点为黑才可能引起红黑树不平衡
		if(originalColor == Color.BLACK)
			deleteRebalance(replacement);
		return true;
	}
	
	public RBTreeNode getBrother(RBTreeNode node)
	{
		RBTreeNode parent = node.getParent();
		if(node == parent.getLeftChild())
			return parent.getRightChild();
		return parent.getLeftChild();
	}
	
	public void deleteRebalance(RBTreeNode node)
	{
		while(node != getRoot() && node.getColor() == Color.BLACK)
		{
			RBTreeNode parent = node.getParent();
			RBTreeNode brother, brotherLeftChild, brotherRightChild;
			
			//左孩子
			if(node == parent.getLeftChild())
			{
				brother = parent.getRightChild();
				brotherLeftChild = brother.getLeftChild();
				brotherRightChild = brother.getRightChild();
				//case 1——兄弟节点为红
				//则反转兄弟结点与父结点的颜色
				//以父结点为轴心左旋
				//更新兄弟结点为原父结点在左旋后的新右孩子
				//因为这个新的右孩子是原兄弟结点的孩子
				//而原兄弟结点为红，其孩子必为黑
				//因此case 1最终会转换成case 2/3/4中的某一种
				if(isRed(brother))
				{
					brother.setColor(Color.BLACK);
					parent.setColor(Color.RED);
					parent.leftRotate();
					brother = parent.getRightChild();
				}
				//case 2——兄弟为黑，且兄弟的两个孩子为黑
				//兄弟变红，欲修复结点上移一层至父结点
				//只有case 2会导致修复点上移
				//上移后的情形不确定，可能是case 1/2/3/4，也可能修复完成
				//修复完成的情况有两种：
				//1、上移后的结点为红，此时跳出循环，并将node置黑
				//2、上移后到达根结点，同样跳出循环，并将node置黑
				else if(!isRed(brotherLeftChild) && !isRed(brotherRightChild))
				{
					brother.setColor(Color.RED);
					node = parent;
				}
				//case 3——兄弟为黑，且兄弟的左孩子为红, 右孩子为黑
				//交换兄弟和其左孩子的颜色
				//再以兄弟为轴点右旋
				//更新兄弟结点为原父结点在右旋后的新右孩子
				//右旋后，变成第4种情况
				else if(!isRed(brotherRightChild))
				{
					brother.setColor(Color.RED);
					brotherLeftChild.setColor(Color.BLACK);
					brother.rightRotate();
					brother = parent.getRightChild();
				}
				//case 4——兄弟为黑，且兄弟的右孩子为红（左孩子红或黑）
				//兄弟结点的颜色改成父结点颜色
				//随后父结点与兄弟的右孩子都涂成黑
				//以父结点为轴心左旋，此时修复完成
				else if(isRed(brotherRightChild))
				{
					brother.setColor(parent.getColor());
					parent.setColor(Color.BLACK);
					brotherRightChild.setColor(Color.BLACK);
					parent.leftRotate();
					node = getRoot();//修复完成，结束循环
				}
			}
			//右孩子
			else
			{
				brother = parent.getLeftChild();
				brotherLeftChild = brother.getLeftChild();
				brotherRightChild = brother.getRightChild();
				//case 1——兄弟节点为红
				if(isRed(brother))
				{
					brother.setColor(Color.BLACK);
					parent.setColor(Color.RED);
					parent.rightRotate();
					brother = parent.getLeftChild();
				}
				//case 2——兄弟为黑，且兄弟的两个孩子为黑
				else if(!isRed(brotherLeftChild) && !isRed(brotherRightChild))
				{
					brother.setColor(Color.RED);
					node = parent;
				}
				//case 3——兄弟为黑，且兄弟的右孩子为红, 左孩子为黑
				else if(!isRed(brotherLeftChild))
				{
					brother.setColor(Color.RED);
					brotherRightChild.setColor(Color.RED);
					brother.rightRotate();
					brother = parent.getLeftChild();
				}
				//case 4——兄弟为黑，且兄弟的左孩子为红（右孩子红或黑）
				else if(isRed(brotherLeftChild))
				{
					brother.setColor(parent.getColor());
					parent.setColor(Color.BLACK);
					brotherLeftChild.setColor(Color.BLACK);
					parent.rightRotate();
					node = getRoot();//修复完成，结束循环
				}
			}
		}
		node.setColor(Color.BLACK);
	}
	
	/**
	 * 树的层序遍历
	 */
	public void layerVisit()
	{
		if(isEmpty())    return;
		Queue<RBTreeNode> queue = new LinkedList<RBTreeNode>();
		queue.offer(getRoot());
		while(!queue.isEmpty())
		{
			RBTreeNode node = queue.poll();
			
			if(node.isNull())
			{
				System.out.println("null");
				continue;
			}
			System.out.println(node);
			
			RBTreeNode left = node.getLeftChild();
			RBTreeNode right = node.getRightChild();
			queue.offer(left);
			queue.offer(right);
		}
	}
}
