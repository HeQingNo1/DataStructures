package linkedlist;

public class MyDoubleLinkedList<E> implements MyList<E> {
    /**
     * 定义节点类 DoubleNode
     */
    class DoubleNode<E> {
        E item;
        DoubleNode<E> prev;
        DoubleNode<E> next;
        DoubleNode(DoubleNode<E> prev,E item,DoubleNode<E> next){
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
     }

    private DoubleNode head;
    private DoubleNode tail;
    private int size;

    /**
     * 将节点添加到尾部
     * @param element
     */
    private void linkLast(E element){
        //获取尾结点
        DoubleNode<E> t = this.tail;
        //创建节点对象
        DoubleNode<E> node = new DoubleNode<>(t,element,null);
        //将新节点定义为尾结点
        this.tail = node;
        if(t == null){
            this.head = node;
        }else{
            t.next = node;
        }
        this.size++;
    }

    /**
     * 检验index合法性
     * @param index
     */
    private void checkLinked(int index){
        if(index<0 || index>=this.size){
            throw new IndexOutOfBoundsException("Index:"+index+" Size:"+this.size);
        }
    }

    /**
     * 根据位置查找节点对象
     * @param index
     * @return
     */
    private DoubleNode<E> getNode(int index){
        DoubleNode<E> node = null;
        if(index < (this.size >> 1)) {
            node = this.head;
            for(int i=0;i<index;i++){
                node = node.next;
            }
        }else{
            node = this.tail;
            for(int i=this.size-1;i>index;i--){
                node = node.prev;
            }
        }
        return node;
    }
    @Override
    public void add(E element) {
        this.linkLast(element);
    }

    /**
     * 在链表头添加节点
     * @param element
     */
    public void addFirst(E element){
        DoubleNode<E> head = this.head;
        DoubleNode<E> node = new DoubleNode<>(null,element,head);
        this.head = node;
        //判断当前链表是否有节点，如果没有 则该节点既是头节点也是尾结点
        if(head == null){
            this.tail = node;
        }else{
            head.prev = node;
        }
        this.size++;
    }

    /**
     * 在链表尾添加节点
     * @param element
     */
    public void addLast(E element){
        this.linkLast(element);
    }
    /**
     *
     * @param index -> [0,size)
     * @return
     */
    @Override
    public E get(int index) {
        //对index做合法性检验
        this.checkLinked(index);

        DoubleNode<E> node = this.getNode(index);
        return node.item;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E remove(int index) {
        this.checkLinked(index);
        DoubleNode<E> node = this.getNode(index);
        E item = node.item;
        //判断当前节点是否为头节点
        if(node.prev == null){
            this.head = node.next;
        }else{
            node.prev.next = node.next;
        }
        //判断当前节点是否为尾节点
        if(node.next == null){
            this.tail = node.prev;
        }else{
            node.next.prev = node.prev;
        }
        node.prev = null;
        node.item = null;
        node.next = null;
        this.size--;
        return item;
    }

    public static void main(String[] args) {
        MyDoubleLinkedList<String> node = new MyDoubleLinkedList<>();
        node.add("a");
        node.add("b");
        node.add("c");
        node.add("d");
        node.add("e");
        System.out.println(node.size());
        System.out.println(node.get(0));
        System.out.println(node.get(1));
        System.out.println(node.get(2));

    }
}
