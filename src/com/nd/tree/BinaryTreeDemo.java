package com.nd.tree;

/**
 * 二叉树(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/22 18:39
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "松江");
        HeroNode node2 = new HeroNode(2, "无用");
        HeroNode node3 = new HeroNode(3, "论均已");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //先手动创建该二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();

//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

//        System.out.println();
//        HeroNode resNode = binaryTree.preSearcher(5);
//        HeroNode resNode = binaryTree.infixSearcher(5);
//        HeroNode resNode = binaryTree.postSearcher(5);
//        if (resNode != null){
//            System.out.println("找到了"+resNode);
//        } else {
//            System.out.println("未找到节点");
//        }
        binaryTree.delNode(3);
        System.out.println("删除后：");
        binaryTree.preOrder();
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

    //前序搜索
    public HeroNode preSearcher(int no){
        if (root != null){
            return root.preSearcher(no);
        } else {
            return null;
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

    //中序搜索
    public HeroNode infixSearcher(int no){
        if (root != null){
            return root.infixSearcher(no);
        } else {
            return null;
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

    //后序搜索
    public HeroNode postSearcher(int no){
        if (root != null){
            return root.postSearcher(no);
        } else {
            return null;
        }
    }

    //删除节点
    public void delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空数");
        }
    }
}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //如果leftType ==0 表示指向的是左子树， 如果是1表示指向前驱节点
    //如果rightType ==0表示指向的是右子树，如果1表示指向后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    //前序搜索
    public HeroNode preSearcher(int no) {
        if (this.no == no){
            return this;
        }
        //向左
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.preSearcher(no);
        }
        if (resNode != null){
            return resNode;
        }

        //向右
        if (this.right != null){
            resNode = this.right.preSearcher(no);
        }
        return resNode;
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

    //中序搜索
    public HeroNode infixSearcher(int no){
        HeroNode res = null;
        //向左
        if (this.left != null) res = this.left.infixSearcher(no);
        if (res != null) return res;
        //中间
        if (this.no == no) return this;
        //向右
        if (this.right != null) res = this.right.infixSearcher(no);
        return res;
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

    //后序搜索
    public HeroNode postSearcher(int no){
        HeroNode res = null;
        //向左
        if (this.left != null) res = this.left.postSearcher(no);
        if (res != null) return res;

        //向右
        if (this.right != null) res = this.right.postSearcher(no);
        if (res != null) return res;

        if (this.no == no) return this;

        return res;
    }

    //删除节点
    //1. 如果删除的是叶子节点，就删除掉该节点
    //2. 如果删除的是非叶子节点，删掉整个子树
    public void delNode(int no){
        //判断左右是不是要删除的节点
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //向左递归删除
        if (this.left != null){
            this.left.delNode(no);
        }
        //向右递归删除
        if (this.right != null){
            this.right.delNode(no);
        }
    }

}