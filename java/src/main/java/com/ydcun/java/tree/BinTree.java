/**
 * 
 */
package com.ydcun.java.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

/**
 *   二叉树实现案例
 *   层序构造二叉树
 *   二叉树，前序 中序 后序 遍历
 *   二叉树树形结构打印
 */
public class BinTree {
	//树节点
	class TreeNode{
		int value;
		TreeNode left;
		TreeNode right;
		int count;
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
	 * 非递归先序遍历
	 */
	public void preOrderTraverse2(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = getRoot();
		stack.push(node);
		while(!stack.isEmpty()){
			node = stack.pop();
			System.out.print(node.value+" ");
			if(node.right!=null){
				stack.push(node.right);
			}
			if(node.left!=null){
				stack.push(node.left);
			}
		}
	}
	/**
	 * 非递归中序遍历
	 */
	public void inorderTraverse2(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = getRoot();
		stack.push(node);
		while(!stack.isEmpty()){
			if(node.left!=null){
				stack.push(node.left);
				node = node.left;
			}else{
				node = stack.pop();
				System.out.print(node.value+" ");
				if(node.right!=null){
					stack.push(node.right);
					node = node.right;
				}
			}
		}
	}
	/**
	 * 非递归后遍历
	 */
	public void postOrderTraverse2(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = getRoot();
		node.count++;
		stack.push(node);
		while(!stack.isEmpty()){
			if(node.left!=null){
				node.left.count++;
				stack.push(node.left);
				node = node.left;
			}else{
				node = stack.pop();
				node.count++;
				if(node.count==3){//第三次访问节点的时候打印
					System.out.print(node.value+" ");
				}
				if(node.count==2){//第二次访问 在放入栈中
					stack.push(node);
				}
				if(node.right!=null){
					node.right.count++;
					stack.push(node.right);
					node = node.right;
				}
			}
		}
	}
	/**
	 * 非递归后序遍历特殊形式
	 * 将先序遍历的左右颠倒，在逆序输出
	 */
	public void postOrderTraverse3(){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = getRoot();
		stack.push(node);
		String sb="";
		while(!stack.isEmpty()){
			node = stack.pop();
			sb = node.value+" "+sb;
			if(node.left!=null){
				stack.push(node.left);
			}
			if(node.right!=null){
				stack.push(node.right);
			}
		}
		System.out.println(sb);
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
		System.out.println("树深度:"+this.getTreeDepth());
		System.out.println("先序遍历递归实现:");
		//先序遍历
		this.preOrderTraverse(this.getRoot());
		System.out.println("\n非递归实现:");
		this.preOrderTraverse2();
		System.out.println();
		System.out.println("\n中序遍历递归实现:");
		//中序遍历
		this.inorderTraverse(this.getRoot());
		System.out.println("\n非递归实现:");
		this.inorderTraverse2();
		System.out.println();
		System.out.println("\n后序遍历递归实现:");
		//后序遍历
		this.postOrderTraverse(this.getRoot());
		System.out.println("\n非递归实现:");
		this.postOrderTraverse2();
		System.out.println("\n非递归特殊情况实现:");
		this.postOrderTraverse3();
		System.out.println();
	}
}
