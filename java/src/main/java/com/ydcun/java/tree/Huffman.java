/**
 * 
 */
package com.ydcun.java.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * huffman编码实现
 * 将array数组中的值进行huffman编码
 */
public class Huffman {
	List<HuffmanNode> nodeList = new ArrayList<HuffmanNode>();
	class HuffmanNode{
		int value;
		int parent,left,right;
		@Override
		public String toString() {
			return ""+value+"\t"+parent+"\t"+left+"\t"+right;
		}
	}
	public Huffman(){}
	public void HuffmanTree(int[] array){
		HuffmanNode node = null;
		//将array保存到nodeList
		for(int i=0;i<array.length*2;i++){
			node = new HuffmanNode();
			if(i>array.length-1){
				node.value = array[i-array.length];
			}
			nodeList.add(node);
		}
		//构建树
		int x,y;//x存最小值下标，y存次小值下标
		int min1,min2;//min1存最小值，min2存次小值
		HuffmanNode tempNode = null;
		for(int i=array.length-1;i>0;i--){
			x=y=0;
			min1=min2=Integer.MAX_VALUE;
			for(int j=i+1;j<nodeList.size();j++){
				tempNode = nodeList.get(j);
				if(tempNode.parent==0){
					if(tempNode.value<=min1){//与最小值比较
						y = x;
						x = j;
						min2 = min1;
						min1 = tempNode.value;
					}else if(tempNode.value<min2){//与次小值比较
						y=j;
						min2 = tempNode.value;
					}
				}
			}
			//将找到的两个最小节点，父节点设置成i，i对应节点做孩子=x，有孩子=y，parent=x.value+y.value
			nodeList.get(i).value = nodeList.get(x).value+nodeList.get(y).value;
			nodeList.get(x).parent=i;
			nodeList.get(y).parent=i;
			nodeList.get(i).left=x;
			nodeList.get(i).right=y;
		}
	}
	public String getCodeing(int x){
		String sb="";
		HuffmanNode temp = null;
		int i;
		for(i=nodeList.size()-1;i>=nodeList.size()/2;i--){
			if(nodeList.get(i).value==x){
				temp = nodeList.get(i);
				break;
			}
		}
		if(temp==null){//没有找到
			return null;
		}
		HuffmanNode parentTemp = null;
		//从找到的节点开始往上找知道父节点值=0
		while(temp.value!=0){
			parentTemp = nodeList.get(temp.parent);
			if(parentTemp.left==i){
				sb="0"+sb;
			}else if(parentTemp.right==i){
				sb="1"+sb;
			}
			i=temp.parent;
			temp = parentTemp;
		}
		
		return sb;
	}
	public void printNodeList(){
		for(int i=0;i<nodeList.size();i++){
			System.out.println(i+"\t"+nodeList.get(i));
		}
	}
	@Test
	public void HuffmanEncoding(){
		int[] array = new int[]{56,34,21,1,45,98};
		this.HuffmanTree(array);
		this.printNodeList();
		for(int i=0;i<array.length;i++)
		System.out.println(this.getCodeing(array[i]));
	}
}
