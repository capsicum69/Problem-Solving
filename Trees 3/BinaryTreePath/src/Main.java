import javax.swing.tree.TreeNode;
import java.util.ArrayList;

public class Solution {

    static boolean pathInATreeUtil(TreeNode root, int x, ArrayList<Integer> res){

        if(root == null) return false;

        res.add(root.data);

        if(root.data == x) return true;

        if(pathInATreeUtil(root.left, x, res) || pathInATreeUtil(root.right, x, res)){

            return true;

        }

        res.remove(res.size() - 1);

        return false;

    }



    public static ArrayList<Integer> pathInATree(TreeNode root, int x) {

        ArrayList<Integer> res = new ArrayList<>();

        if(root == null) return res;

        pathInATreeUtil(root, x, res);

        return res;

    }

}