package com.nd.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/26 15:25
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }

    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }

    public static Node createHuffmanTree(int[] arr){
        //1. 遍历arr数组
        //2. 将arr的每个元素构建成一个node
        //3.将node放入到arraylist中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value: arr){
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1){
            //排序
            Collections.sort(nodes);

            //取出权值最小的两颗二叉树
            //取出权值最小的两个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //删除掉处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将parent加入到nodes
            nodes.add(parent);
        }

        //返回赫夫曼树的头节点
        return nodes.get(0);
    }
}

//创建节点类
//为了让Node对象支持排序Collections
class Node implements Comparable<Node> {
    int value; //节点权值
    Node left; //左子节点
    Node right; //右子节点

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.value - o.value;
    }
}