package com.nd.tree;

/**
 * 顺序存储二叉树(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/23 16:43
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOder();
        System.out.println();
        arrayBinaryTree.infixOder(0);
        System.out.println();
        arrayBinaryTree.postOder(0);
    }
}

class ArrayBinaryTree{
    private int[] arr; //存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOder() {
        this.preOder(0);
    }

    //编写顺序存储二叉树的前序遍历
    public void preOder(int index){
        //如果数组为空 或者 arr.length = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        System.out.println(arr[index]);
        //向左递归
        if (2 * index + 1 < arr.length) preOder(2 * index + 1);

        //向右递归
        if (2 * index + 2 < arr.length) preOder(2 * index + 2);

    }

    //编写中序存储二叉树的前序遍历
    public void infixOder(int index){
        //如果数组为空 或者 arr.length = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        //向左递归
        if (2 * index + 1 < arr.length) infixOder(2 * index + 1);

        System.out.println(arr[index]);
        //向右递归
        if (2 * index + 2 < arr.length) infixOder(2 * index + 2);
    }

    //编写后序存储二叉树的前序遍历
    public void postOder(int index){
        //如果数组为空 或者 arr.length = 0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        //向左递归
        if (2 * index + 1 < arr.length) postOder(2 * index + 1);
        //向右递归
        if (2 * index + 2 < arr.length) postOder(2 * index + 2);
        System.out.println(arr[index]);
    }
}
