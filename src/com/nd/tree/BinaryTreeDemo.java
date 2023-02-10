package com.nd.tree;

/**
 * 二叉树(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/22 18:39
 */
public class BinaryTree {
    public static void main(String[] args) {

    }
}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        //递归向左子树前序遍历
        if (this.left!=null){
            this.left.preOrder();
        }
        //递归向右子树遍历
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    //定义一个二叉树
    class BinaryTree{
        private HeroNode root;

        public void setRoot(HeroNode root) {
            this.root = root;
        }

        //前序遍历
        public void preOrder(){
            if (this.root!=null){
                this.root.preOrder();
            } else {
                System.out.println("二叉树为空无法遍历");
            }
        }

        //中序遍历
        public void infixOrder(){
            if (this.root!=null){
                this.root.infixOrder();
            } else {
                System.out.println("二叉树为空无法遍历");
            }
        }

        //后序遍历
        public void postOrder(){
            if (this.root!=null){
                this.root.postOrder();
            } else {
                System.out.println("二叉树为空无法遍历");
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        //递归向左子树右序遍历
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //递归向左子树后序遍历
        if (this.left!=null){
            this.left.postOrder();
        }

        //递归向左子树后序遍历
        if (this.right!=null){
            this.right.postOrder();
        }

        System.out.println(this);
    }

}