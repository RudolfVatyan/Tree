import java.util.Stack;

public class Tree {
    private Node root;

    public Tree(){
        root = null;
    }

    public boolean insert(int value){
        Node new_node = new Node(value);
        if (root == null){
            root = new_node;
            return true;
        }
        Node tmp = root;
        while (true){
            if (value < tmp.data){
                if (tmp.left == null)
                {
                    tmp.left = new_node;
                    return true;
                }
                tmp = tmp.left;
            } else if (value > tmp.data){
                if (tmp.right == null){
                    tmp.right = new_node;
                    return true;
                }
                tmp = tmp.right;
            } else return false;
        }
    }

    public void inOrder(){
        if (root == null){
            return;
        }
        Node tmp = root;
        Stack<Node> s = new Stack<>();
        while (tmp != null || s.size() > 0){
            while (tmp != null){
                s.push(tmp);
                tmp = tmp.left;
            }
            tmp = s.pop();
            System.out.println(tmp.data + " ");
            tmp = tmp.right;
        }
    }
    private int heightCalc(Node root){
        if (root == null){
            return 0;
        }
        else {
            int left_root = heightCalc(root.left);
            int right_root = heightCalc(root.right);
            return Math.max(left_root,right_root) + 1;
        }
    }
    public int height(){
        return heightCalc(this.root);
    }
    private boolean isBalancedPrivate(Node root){
        if (root == null){
            return true;
        }
        int right_root = heightCalc(root.right);
        int left_root = heightCalc(root.left);
        if (Math.abs(right_root - left_root) <= 1 && isBalancedPrivate(root.left) && isBalancedPrivate(root.right)){
            return true;
        } return false;
    }
    public boolean isBalanced(){
        return isBalancedPrivate(this.root);
    }
    private boolean identicalPrivate(Node This,Node other){
        if ((This == null && other == null)){
            return true;
        }
        if (This != null && other != null){
            return (This.data == other.data && identicalPrivate(This.left, other.left) && identicalPrivate(This.right, other.right)) ;
        }
        return false;
    }
    public boolean identical(Tree other){
        return identicalPrivate(this.root, other.root);
    }
    public boolean isSubtree(Tree other){
        return isSubtreePrivate(this.root, other.root);
    }
    private boolean isSubtreePrivate(Node main, Node sub){
        if (sub == null && main == null){
            return true;
        }
        if (sub != null && main != null){
            if (identicalPrivate(main, sub) || isSubtreePrivate(main.right, sub) || isSubtreePrivate(main.left, sub)){
                return true;
            }
        }
        return false;
    }
}

