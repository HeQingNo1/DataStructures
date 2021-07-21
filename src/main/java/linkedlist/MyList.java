package linkedlist;

/**
 * 基于链表结构存取元素的方法API定义
 * @param <E>
 */
public interface MyList<E> {
    void add(E element);

    E get(int index);

    int size();

    E remove(int index);
}
