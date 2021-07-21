package linkedlist;

public class MySingleLinkedList<E> implements MyList<E> {
    /**
     * 节点内部类
     */
    class Node<E>{
        private Node next;
        private E item;

        Node(E item,Node next){
            this.item = item;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    /**
     * 获取尾结点
     * @return
     */
    private Node getTail(){
        if(this.head == null){
            return null;
        }

        Node tail = this.head;
        while(true) {
            if (tail.next == null) break;

            tail = tail.next;
        }

        return tail;

    }

    @Override
    public void add(E element) {
        Node node = new Node(element,null);

        Node tail = getTail();
        if(tail == null){
            this.head = node;
        }else{
            tail.next = node;
        }

        size++;
    }

    /**
     *校验index合法性
     * @param index
     */
    private void checkIndex(int index){
        if(index >= this.size || index < 0)
            throw new IndexOutOfBoundsException("Index "+index+" "+"size "+this.size);
    }

    /**
     * 获取指定索引的节点
     * @param index
     * @return
     */
    private Node<E> getNode(int index){
        int num = 0;
        Node node = this.head;
        while(true){
            if(num == index) break;
            node = node.next;
            num++;
        }
        /**
         * for(int i=0;i<index;i++) node = node.next;
         */
        return node;
    }
    @Override
    public E get(int index) {
        //校验index合法性
        this.checkIndex(index);

        Node<E> node = this.getNode(index);

        return node.item;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E remove(int index) {
        //校验Index的合法性
        this.checkIndex(index);
        //根据位置找到该节点对象
        Node<E> node = this.getNode(index);
        E item = node.item;
        //将该节点对象从单向链表中移除
          //判断当前删除的节点是否为头节点
        if(this.head == node){
            this.head = node.next;
        }else{
            Node<E> temp = this.head;
            for(int i = 0;i<index;i++){
                temp = temp.next;
            }
            temp.next = node.next;
        }
        node.next = null;
        //记录元素个数
        this.size--;
        //返回删除元素
        return item;
    }

    public static void main(String[] args) {
        MySingleLinkedList<String> list = new MySingleLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println(list.size());
        System.out.println(list.get(2));
        System.out.println(list.remove(0));
        System.out.println("---------------------------");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println("---------------------------");
        System.out.println(list.size());
        System.out.println(list.get(2));
        System.out.println("---------------------------");
        System.out.println(list.get(3));
    }


}


