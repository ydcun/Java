/**
 * 
 */
package com.ydcun.java.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author ydcun-psjs
 *
 */
public class BinTree {
	//树节点
	class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;
		public TreeNode(){}
		public TreeNode(int value){
			this.value = value;
		}
		public TreeNode(int value,TreeNode left,TreeNode right){
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
	
	private TreeNode root;
	
	/**
	 * 层序的方式构建二叉树
	 */
	public void initLevel(int[] array){
		TreeNode[] tempArray = new TreeNode[array.length];
		TreeNode temp;
		TreeNode left;
		TreeNode right;
		for(int i=0;i<=array.length/2-1;i++){
			temp = tempArray[i];
			if(temp==null){
				temp = new TreeNode(array[i]);
			}
			tempArray[i] = temp;
			int leftIndex = 2*i+1;
			left = new TreeNode(array[leftIndex]);
			tempArray[leftIndex] = left;
			temp.left=left;
			if(leftIndex+1!=array.length){
				right = new TreeNode(array[leftIndex+1]);
				tempArray[leftIndex+1] = right;
				temp.right = right;
			}
		}
		root = tempArray[0];
	}
	/**
	 * @return 获取树的深度
	 */
	public int getTreeDepth(){
		if(getRoot()==null){
			System.out.println("树为空");
			return 0;
		}
		int depth=0;
		List<TreeNode> list = new ArrayList<TreeNode>();
		list.add(root);
		while(list.size()!=0){
			int count = list.size();
			for(int i=0;i<count;i++){
				TreeNode node = list.remove(0);	
				if(node!=null && node.left!=null){
					list.add(node.left);
				}
				if(node!=null && node.right!=null){
					list.add(node.right);
				}
			}
			depth++;
		}
		return depth;
	}
	public void printTree(){
		if(getRoot()==null){
			System.out.println("树为空");
			return;
		}
		//获取树的深度
		int depth = getTreeDepth();
		int count=1;
		List<TreeNode> list = new ArrayList<TreeNode>();
		list.add(root);
		while(list.size()!=0){
			int sum = list.size();
			for(int d=1;d<(int)Math.pow(2,depth-count);d++) System.out.print("  ");
			for(int i=0;i<sum;i++){
				TreeNode node = list.remove(0);	
				System.out.printf("%2d",node.value);//格式化输出
				for(int d=1;d<(int)Math.pow(2,depth-count+1);d++) System.out.print("  ");
				if(node!=null && node.left!=null){
					list.add(node.left);
				}
				if(node!=null && node.right!=null){
					list.add(node.right);
				}
			}
			count++;
			System.out.println();
		}
	}
	/**
	 * 递归先序遍历
	 */
	public void preOrderTraverse(TreeNode node){
		if(node==null){
			return;
		}
		System.out.print(node.value+" ");
		preOrderTraverse(node.left);
		preOrderTraverse(node.right);
	}
	/**
	 * 递归中序遍历
	 */
	public void inorderTraverse(TreeNode node){
		if(node==null){
			return;
		}
		inorderTraverse(node.left);
		System.out.print(node.value+" ");
		inorderTraverse(node.right);
	}
	/**
	 * 递归后序遍历
	 */
	public void postOrderTraverse(TreeNode node){
		if(node==null){
			return;
		}
		postOrderTraverse(node.left);
		postOrderTraverse(node.right);
		System.out.print(node.value+" ");
	}
	
	/**
	 * @return 返回树根节点
	 */
	public TreeNode getRoot(){
		return root;
	}
	
	@Test
	public void test(){
		int[] array = {1,2,3,4,5,6,7,8,9,10,2,8,9,10,2,8,9,10,2,8,9,10,2,8,9,10,2,8,9,10,2};
		this.initLevel(array);
		this.printTree();
		System.out.println(this.getTreeDepth());
		System.out.println("先序遍历(递归):");
		//先序遍历
		this.preOrderTraverse(this.getRoot());
		System.out.println();
		System.out.println("中序遍历(递归):");
		//中序遍历
		this.inorderTraverse(this.getRoot());
		System.out.println();
		System.out.println("后序遍历(递归):");
		//后序遍历
		this.postOrderTraverse(this.getRoot());
	}
}
