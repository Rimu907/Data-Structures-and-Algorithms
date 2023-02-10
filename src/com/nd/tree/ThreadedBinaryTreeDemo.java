package com.nd.tree;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/24 15:20
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        //threadedBinaryTree.threadedNodes(root);
        threadedBinaryTree.preThreadedNodes(root);

        threadedBinaryTree.preThreadedList(root);

    }
}

class ThreadedBinaryTree extends BinaryTree {
    //为了实现线索化，需要创建要指向当前节点的前驱节点的指针
    //总是保存前一个节点
    private HeroNode pre;
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //遍历线索化二叉树
    public void preThreadedList(HeroNode node){
        //存储当前遍历的节点
        if (node == null){
            return;
        }
        if (node.getLeftType() == 1 || node.getRightType() == 1){
            System.out.println(node);
            return;
        }
        System.out.println(node);
        preThreadedList(node.getLeft());
        preThreadedList(node.getRight());
    }


    //对二叉树进行中序线索化的方法
    public void threadedNodes(HeroNode node){
        if (node == null){
            return;
        }
        //1.线索化左子树
        threadedNodes(node.getLeft());
        //2.线索化当前节点

        //处理当前节点的前驱节点
        if (node.getLeft() == null){
            //当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //处理当前节点的后继节点
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

        //3.线索化右子树
        threadedNodes(node.getRight());
    }

    //对二叉树进行前序线索化的方法
    public void preThreadedNodes(HeroNode node){
        if (node == null){
            return;
        }

        //2.线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null){
            //当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //处理当前节点的后继节点
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

        //1.线索化左子树
        if (node.getLeftType()==0){
            preThreadedNodes(node.getLeft());
        }
        //3.线索化右子树
        if (node.getRightType()==0){
            preThreadedNodes(node.getRight());
        }
    }

    //对二叉树进行后序线索化的方法
    public void postThreadedNodes(HeroNode node){
        if (node == null){
            return;
        }

        //1.线索化左子树
        if (node.getLeftType() == 0){
            postThreadedNodes(node.getLeft());
        }

        //2.线索化右子树
        if (node.getRightType() == 0){
            postThreadedNodes(node.getRight());
        }

        //2.线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft() == null){
            //当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型
            node.setLeftType(1);
        }
        //处理当前节点的后继节点
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;
    }
}