package com.ydcun.java.collections;

/**
 * Created by ydcun-pro on 2017/8/2.
 */
class Node{
    public int i;
    public Node nextNode = null;
    Node(int i){
        this.i = i;
    }
}
public class ReversalLinkList {
    public static void main(String[] arge){
        Node root = new Node(1);
        Node temp = root;
        for(int i=2;i<=10;i++){
            temp.nextNode = new Node(i);
            temp = temp.nextNode;
        }
        printLink(root);

        root = reverseLink(root);
        printLink(root);

    }
    public static Node reverseLink(Node root){
        Node t1 = root;
        Node t2 = t1.nextNode;
        Node hard = t1;

        while(t1.nextNode!=null){
            t1.nextNode = t2.nextNode;
            t2.nextNode = hard;
            hard = t2;
            t2 = t1.nextNode;
        }
        return hard;
    }
    public static void printLink(Node root){
        System.out.println();
        Node temp = root;
        while(temp.nextNode!=null){
            System.out.print(temp.i+" ");
            temp = temp.nextNode;
        }
        System.out.print(temp.i);
    }
}
