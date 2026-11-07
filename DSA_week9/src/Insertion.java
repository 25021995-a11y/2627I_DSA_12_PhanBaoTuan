import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

    public static void preOrder( Node root ) {

        if( root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

 /* Node is defined as :
 class Node 
    int data;
    Node left;
    Node right;
    
    */

    public static Node insert(Node root,int data) {
        if(root==null){return new Node(data);}
        Node oriNode=root;
        Node cur=root;
        while(true){
            if(data<cur.data && cur.left==null){
                cur.left=new Node(data);
                break;
            }
            else if(data<cur.data && cur.left!=null){cur=cur.left;}
            else if(data>=cur.data && cur.right==null){
                cur.right=new Node(data);
                break;
            }
            else if(data>=cur.data &&cur.right!=null){cur=cur.right;}
        }
        return oriNode;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        preOrder(root);
    }
}