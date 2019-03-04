package review.Tree;

/**
 * 二叉搜索树(又称二叉排序树)节点的查找/插入/删除
 */
public class BinarySortTree {

    public static void main(String[] args){

        BinarySortTree sortTree = new BinarySortTree();
        Integer[] nums = {54,18,12,36,6,81,76,99,57,66,20,19};

        BinaryTreeNode<Integer> root = sortTree.makeBinarySortTree(nums);

        sortTree.Delete(root,54);

        BinaryTreeNode.MiddlePrint(root);

        System.out.println();

        BinaryTreeNode.FrontPrint(root);

    }

    /**
     * 根据输入的一个数组建立二叉搜索树
     * @param num
     * @return
     */
    public BinaryTreeNode<Integer> makeBinarySortTree(Integer[] nums){
        BinaryTreeNode<Integer> root = null;
        for(int i=0;i<nums.length;i++){
            root = Insert(root,nums[i]);
        }
        return root;
    }

    /**
     * 向二叉搜索树种插入一个数值为data的节点
     * @param root
     * @param data
     * @return
     */
    public BinaryTreeNode<Integer> Insert(BinaryTreeNode<Integer> root, Integer data){

        if(root==null)
            // 直接新建节点返回
            return new BinaryTreeNode<>(data);

        // 查找到插入该节点的位置
        BinaryTreeNode<Integer> node = root;

        while (true) {
            if (node.data < data) {
                if(node.right==null) {
                    // 插入该节点,新节点的父亲节点是node
                    node.right = new BinaryTreeNode<Integer>(data,node);
                    return root;
                }
                // 右节点
                node = node.right;
            } else {
                if(node.left==null) {
                    // 插入该节点,新节点的父亲节点是node
                    node.left = new BinaryTreeNode<>(data,node);
                    return root;
                }
                // 左节点
                node = node.left;
            }
        }
    }

    /**
     * 查找二叉搜索树中的某一节点
     * @return
     */
    public BinaryTreeNode<Integer> Find(BinaryTreeNode<Integer> root, Integer data){
        BinaryTreeNode<Integer> node = root;

        while (node != null) {
            if (data > node.data) {
                // 右节点
                node = node.right;
            } else if (data < node.data) {
                // 左节点
                node = node.left;
            }else {
                return node;
            }
        }

        // 如果没找到目标节点,那么返回Null,如果找到了,就返回目标
        return node;
    }

    /**
     * 在二叉搜索树中删除一个节点值为data的节点p
     * 步骤:
     *      1. 如果要删除的节点是叶子节点:
     *          a. 直接删除
     *      2. 如果要删除的节点度数为1(即有一个孩子节点),删除p节点并用p的孩子顶替其作为其父母的孩子
     *          a. 如果p是父母的左孩子,设置p.parent.left = p.child(左右孩子都可以)
     *          b. 如果p是父母的右孩子,设置p.parent.right = p.child(左右孩子都可以)
     *      3. p节点度数为2
     *          不直接删除一个2度的节点,而是使用p节点在中根次序下的后继节点的insucc值代替p节点值,
     *          再删除insucc节点,这样删除2度节点的问题就转化为了删除1度节点或叶子节点的问题.
     *          如果p的右孩子是叶子节点,那么insucc是p的右孩子;
     *          否则insucc是p的右孩子的最左边的一个子孙节点,insucc的度为0或1
     *
     * @param root
     * @param data
     * @return
     */
    public BinaryTreeNode<Integer> Delete(BinaryTreeNode<Integer> root, Integer data){
        // 第一步,找到要删除的节点
        BinaryTreeNode<Integer> node = Find(root,data);

        // 判断该节点的度数
        if(node.left==null && node.right==null){
            // 度数为0,直接删除该节点
            if(node.parent.left==node) node.parent.left = null;
            else node.parent.right = null;
        }else if(node.left == null){
            // 度数为1
            if(node.parent.left==node){
                // 要删除的节点是其父亲节点的左孩子
                node.parent.left = node.right;
            }else{
                // 要删除的节点是其父亲节点的右孩子
                node.parent.right = node.right;
            }
        }else {
            // 度数为2

            if(node.right!=null && node.right.right==null && node.right.left==null){
                // 判断点p的右孩子节点是否是叶子节点,如果是,用右孩子节点的值代替点p,同时删除右孩子节点
                node.data = node.right.data;

                // 删除右孩子节点
                node.right = null;
            }else {
                // 使用点p左孩子最右边的子孙节点代替点p(即左孩子中值最大的数)
                // 找到insucc节点
                BinaryTreeNode<Integer> insucc = node.left;
                while (insucc.right!=null){
                    insucc = insucc.right;
                }

                // 此时insucc是左孩子中值最大的节点,它的度数可能为1或0
                if(insucc.left == null){
                    // 度数为0的情况
                    node.data = insucc.data;
                    insucc.parent.left = null;
                }else {
                    // 度数为1的情况(即insucc具有左孩子)
                    node.data = insucc.data;
                    if(insucc.parent.left == insucc){
                        // insucc是父母的左孩子
                        insucc.parent.left = insucc.left;
                    }else if(insucc.parent.right==insucc){
                        // insucc是父母的右孩子
                        insucc.parent.right = insucc.left;
                    }
                }
            }
        }

        return root;
    }

}
