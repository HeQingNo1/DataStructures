package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(1, "hq", "天霸");
        //创建链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        //加入(考虑编号顺序)
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.list();
//
//        //测试修改
//        doubleLinkedList.update(hero4);
//        System.out.println("修改后的链表：");
//        doubleLinkedList.list();
//
//        //测试删除
//        doubleLinkedList.delete(1);
//        System.out.println("删除节点后的链表：");
//        doubleLinkedList.list();
//
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.list();

        //顺序加入(考虑编号顺序)
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.list();

    }
}

//定义DoubleLinkedList管理我们的英雄
class DoubleLinkedList{
    //先初始化一个头节点，头节点不能动
    private HeroNode2 head = new HeroNode2(0,"","");

    /**
     * 返回头节点
     */
     public HeroNode2 getHead(){
         return head;
    }

    /**
     * 添加节点到单向链表
     * 思路：（当不考虑编号顺序时）1.找到当前链表的最后节点 2.将最后这个节点的next 指向新的节点
     */
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此需要一个辅助变量temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 顺序添加
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
        boolean flag = false;//标志添加的编号是否存在，默认是false
        while (true){
            if(temp.next == null){//说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no){//找到位置
                break;
            }else if(temp.next.no == heroNode.no){ //说明希望添加的HeroNode的编号已存在
                flag = true;
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        if(flag){
            System.out.printf("已存在相同编号%d的元素，添加失败\n",heroNode.no);
        }else{
            heroNode.pre = temp;
            temp.next = heroNode;
            }
        }

    /**
     * 修改节点信息，根据编号no修改
     */
    public void update(HeroNode2 heroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no
        //定义一个辅助节点
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {//已经遍历完链表
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){//找到
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        }else{//没有
            System.out.printf("不存在编号为%d的元素\n",heroNode.no);
        }
    }

    /**
     * 删除节点
     * 比较时，是temp.next.no 和 需要删除的节点的no比较
     */
    public void delete(int no){
        HeroNode2 temp = head.next;
        boolean flag = false;//
        while (true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("删除的节点不存在");
        }
    }

    /**
     * 显示链表（遍历）
     */
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null){
                break;
            }
            System.out.println(temp);
            //将temp后移(一定要)
            temp = temp.next;
        }
    }
}

//定义heroNode,每个heroNode 对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向前一个节点

    //构造器
    public HeroNode2(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方便，重新toString
    @Override
    public String toString(){
        return "HeroNode2 [no=" + no + ",name=" + name + ",nickName=" + nickName +"]";
    }
}