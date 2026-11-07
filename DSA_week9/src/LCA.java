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

    /*
    class Node 
        int data;
        Node left;
        Node right;
    */
    public static Node lca(Node root, int v1, int v2) {
        // Write your code here.
        Node p1=root;
        Node p2=root;
        if(p1.data==v1 && p2.data==v2){return root;}
        Node cur=null;
        while(p1==p2){
            cur=p1;
            if(v1<p1.data){p1=p1.left;}
            else if(v1>p1.data){p1=p1.right;}
            if(v2<p2.data){p2=p2.left;}
            else if(v2>p2.data){p2=p2.right;}
        }
        return cur;
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node ans = lca(root,v1,v2);
        System.out.println(ans.data);
    }
}