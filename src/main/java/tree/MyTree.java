package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于树形结构实现元素存储的容器
 * @param <E>
 */
public class MyTree<E> {
    /**
     * E->E 子节点映射父节点的容器
     */
    private Map<E,E> map = new HashMap<>();
    /**
     * E->List<E> 父节点映射子节点的容器
     */
    private Map<E,List<E>> map2 = new HashMap<>();

    /**
     * 向容器添加元素
     * @param parent
     * @param item
     */
    public void add(E parent,E item){
        this.map.put(item,parent); //单节点映射
        //完成多节点之间的映射
        List<E> list = this.map2.get(parent);
        //判断当前父节点是否有子节点，如果没有则创建新的List
        if(list == null){
            list = new ArrayList<>();
            this.map2.put(parent,list);
        }
        list.add(item);
    }

    /**
     * 获取当前节点的父节点
     * @param item
     * @return
     */
    public E getParent(E item){
        return this.map.get(item);
    }

    /**
     * 获取当前节点的子节点
     * @param item
     * @return
     */
    public List<E> getChild(E item){
        return this.map2.get(item);
    }

    /**
     * 获取当前节点的兄弟节点
     * @param item
     * @return
     */
    public List<E> getBrother(E item){
        ArrayList<E> brotherList = new ArrayList<>();
        E parent = this.getParent(item);
        List<E> list = this.getChild(parent);
        if(list != null) {
//            for (E le : list) {
//                if (le != item)
//                    brotherList.add(le);
//            }
            brotherList.addAll(list);
            brotherList.remove(item);
        }

        return brotherList;
    }

    /**
     * 获取当前节点的祖先节点
     * @param item
     * @return
     */
    public List<E> getForefathers(E item){
        E parent = this.getParent(item);
        //结束递归的边界条件
        if(parent == null){
            return new ArrayList<>();
        }
        List<E> list = this.getForefathers(parent);
        list.add(parent);
        return list;
    }

    /**
     * 获取当前节点的子孙节点
     * @return
     */
    public List<E> getGrandChildren(E item){
        //存放所有子节点中的元素
        ArrayList<E> list = new ArrayList<>();
        List<E> children = this.getChild(item);
        //结束递归的边界条件
        if(children == null){
            return new ArrayList<>();
        }
        for(E child:children){
            List<E> temp = this.getGrandChildren(child);
            list.add(child);
            list.addAll(temp);
        }
        return list;
    }

    public static void main(String[] args) {
        MyTree<String> tree = new MyTree<>();
        tree.add("root","生物");
        tree.add("生物","植物");
        tree.add("生物","动物");
        tree.add("动物","脊索动物");
        tree.add("动物","脊椎动物");
        tree.add("动物","肛肠动物");
        System.out.println(tree.getBrother("脊索动物").toString());
        System.out.println("----------------------");
        System.out.println(tree.getForefathers("脊索动物").toString());
        System.out.println("----------------------");
        System.out.println(tree.getGrandChildren("root").toString());
        System.out.println("----------------------");

    }
}
