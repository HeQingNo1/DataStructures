package com.hq.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "hq", "天霸");
        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        /**加入(不考虑编号顺序)
         * singleLinkedList.add(hero2);
         * singleLinkedList.add(hero1);
         * singleLinkedList.add(hero3);
         */

        //加入(考虑编号顺序)
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();
        //测试 添加相同编号的元素
//        singleLinkedList.addByOrder(hero3);

        //测试修改
//        singleLinkedList.update(hero4);
//        System.out.println("修改后的链表：");
//        singleLinkedList.list();

        //测试删除
//        singleLinkedList.delete(1);
//        System.out.println("删除节点后的链表：");
//        singleLinkedList.list();

        //测试单链表反转
//        singleLinkedList.reverseList();
//        System.out.println("反转节点后的链表：");
//        singleLinkedList.list();

        //测试逆序打印
        System.out.println("逆序打印：");
        singleLinkedList.reversePrint();
    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不能动
    private HeroNode head = new HeroNode(0,"","");

    /**
     * 添加节点到单向链表
     * 思路：（当不考虑编号顺序时）1.找到当前链表的最后节点 2.将最后这个节点的next 指向新的节点
     */
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后的这个节点的next指向新的节点
        temp.next = heroNode;
    }

    /**
     * 顺序添加 第二种方式 在添加英雄时，根据排名将英雄插入到指定位置（如果有相同排名，添加失败)
     */
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此需要一个辅助指针（变量）来帮助找到添加的位置
        //单链表，因此找的temp位于添加位置的前一个节点，否则插入不了。
        /** 思路1
         *         HeroNode temp = head;
         *         while (true){
         *             if(temp.next == null){//说明temp已经在链表的最后
         *                 heroNode.next = null;
         *                 temp.next = heroNode;
         *                 break;
         *             }
         *             if(temp.next.no > heroNode.no){//找到位置
         *                 heroNode.next = temp.next;
         *                 temp.next = heroNode;
         *                 break;
         *             }else if(temp.next.no == heroNode.no){ //说明希望添加的HeroNode的编号已存在
         *                 System.out.printf("已存在相同编号%d的元素，添加失败\t",heroNode.no);
         *                 break;
         *             }else {
         *                 temp = temp.next;//后移，遍历当前链表
         *             }
         *         }
         *     }
         */
        HeroNode temp = head;
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
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点信息，根据编号no修改
     */
    public void update(HeroNode heroNode){
        //判断是否空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no
        //定义一个辅助节点
        HeroNode temp = head.next;
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
        HeroNode temp = head;
        boolean flag = false;//
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("删除的节点不存在");
        }
    }

    /**
     * 链表反转
     */
    public void reverseList(){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }
        //定义辅助指针（变量），帮忙遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点【cur】的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，放到新的链表reverseHead的最前端
        while (cur != null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 逆序打印
     */
    public void reversePrint(){
        if(head.next == null){
            return;
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将节点压入栈
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());//stack的特点是先进后出
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
        HeroNode temp = head.next;
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
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方便，重新toString
    @Override
    public String toString(){
        return "HeroNode [no=" + no + ",name=" + name + ",nickName=" + nickName +"]";
    }
}