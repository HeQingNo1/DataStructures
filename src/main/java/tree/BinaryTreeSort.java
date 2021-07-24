package tree;

public class BinaryTreeSort<E extends Integer> {
    class Node<E extends Integer>{
        private E item;
        private Node left;
        private Node right;

        Node(E item){
            this.item = item;
        }

        /**
         * 添加节点
         * @param node
         */
        public void addNode(Node node){
            //新节点小于当前节点，新节点放在当前节点左边
            if(node.item.intValue() < this.item.intValue()) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.addNode(node);
                }
            }else{
                //新节点大于当前节点，新节点放在当前节点右边
                if(this.right == null){
                    this.right = node;
                }else{
                    this.right.addNode(node);
                }
            }
        }

        /**
         * 使用中序遍历
         */
        public void inorderTraversal(){
            //找到最左侧的节点
            if(this.left != null)
                this.left.inorderTraversal();
            System.out.println(this.item);
            if(this.right != null)
                this.right.inorderTraversal();
        }


    }

    private Node root; //根节点

    /**
     * 添加元素
     * @param element
     */
    public void add(E element){
        Node<E> node = new Node(element);
        if(this.root == null)
            this.root = node;
        else
            this.root.addNode(node);
    }

    /**
     * 对元素进行排序
     */
    public void sort(){
        if(this.root == null) return;
        this.root.inorderTraversal();
    }

    public static void main(String[] args) {
        BinaryTreeSort<Integer> sort = new BinaryTreeSort<>();
        sort.add(1);
        sort.add(3);
        sort.add(2);
        sort.add(5);
        sort.add(8);
        sort.sort();

    }
}
