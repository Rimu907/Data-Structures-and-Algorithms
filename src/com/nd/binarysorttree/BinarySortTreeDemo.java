package com.nd.binarysorttree;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/28 18:03
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int arr[] = {7,3,10,12,5,1,9,0};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i: arr) {
            binarySortTree.add(new Node(i));
        }
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        binarySortTree.delNode(7);
        System.out.println("删除节点后");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree{
    private Node root;

    //查找要删除的节点
    public Node search(int value){
        if (root == null){
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找要删除的父节点
    public Node searchParent(int value){
        if(root == null){
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * @description: 返回最小值，删除该节点
     * @param node 传入的节点
     * @return int 返回的以node为根节点的二叉排序树的最小节点的值
     * @author: NANDI_GUO
     * @date: 2022/11/29 17:30
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左子树，找最小值
        while(target.left!=null){
            target = target.left;
        }
        //这时target指向最小节点
        //删除最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value){
        if (root == null){
            return;
        } else {
            Node targetNode = search(value); //先找到要删除的节点
            //如果没有找到要删除的节点
            if (targetNode == null) return;
            //如果targetNode没有父节点,也就是整棵树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //找到tagertNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null){
                //判断targetNode是parentNode的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value){ //是左
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value){//是右
                    parent.right = null;
                }
            } else if (targetNode.right != null && targetNode.left != null){ //删除有两棵子树的节点
                int min = delRightTreeMin(targetNode.right);
                targetNode.value = min;
            } else { //删除只有一颗子树的节点
                //如果要删除的节点有左子节点
                if (targetNode.left != null){
                    if (parent != null){
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value){
                            parent.left = targetNode.left;
                        } else { //如果targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { //如果要删除的节点有右子节点
                    if (parent != null){
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value){
                            parent.left = targetNode.right;
                        } else { //如果targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    public void add(Node node) {
        if (root == null){
            root = node;
        } else {
            root.add(node);
        }
    }
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }
    }
}


class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的节点
    /**
     * @description:
     * @param value 要删除的节点的值
     * @return com.nd.binarysorttree.Node
     * @author: NANDI_GUO
     * @date: 2022/11/29 14:02
     */
    public Node search(int value){
        if (value == this.value) {
            return this;
        } else if (value < this.value){ //如果查找的值小于当前节点，向左递归查找
            //如果左子节点为空
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    /**
     * @description:
     * @param value 要找的节点值
     * @return com.nd.binarysorttree.Node 返回的是要删除的节点的父节点
     * @author: NANDI_GUO
     * @date: 2022/11/29 14:07
     */
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    //添加节点 递归添加 满足二叉
    public void add(Node node){
        if (node == null){
            return;
        }
        //判断传入的节点的值
        if (node.value < this.value){
            //如果当前节点的左子节点为空
            if (this.left == null){
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else { //添加的节点的值大于等于当前节点的值
            if (this.right == null){
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
