package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;

public class SeperateUtil {

    int[] part;       //分割的长度
    int totalLength;  //管材长度
    int count = 0;
    List<Integer> lossList = new ArrayList<>();
    List<Integer> currentCount = new ArrayList<>();
    List<List<Integer>> allCount = new ArrayList<>();

    public List<Integer> getLossList() {
        return lossList;
    }

    public void setLossList(List<Integer> lossList) {
        this.lossList = lossList;
    }

    public List<List<Integer>> getAllCount() {
        return allCount;
    }

    public void setAllCount(List<List<Integer>> allCount) {
        this.allCount = allCount;
    }

    public SeperateUtil(int totalLength, int[] part) {
        this.totalLength = totalLength;
        this.part = part;
    }

    public void seperate () {
        this.seperate(this.totalLength, 0);
    }

    /**
     *
     * @param left 剩余管材长度
     * @param idx  切割到part的第几层
     */
    private void seperate(int left, int idx) {
        //到达最后一层
        if (1 == part.length - idx) {
            System.out.println("idx: "+ idx);
            int part1200 = left / part[idx]; //最后一层可切割的数量
            System.out.println("part1200:" + part1200);
            int leftLast = left - part1200 * part[idx]; //最后的余料
            this.count = this.count + 1;
            lossList.add(leftLast);
            currentCount.add(part1200);
            System.out.println("part1200:" + currentCount.toString());
            List<Integer> currentCountTemp = new ArrayList<>();
            this.copyList(currentCount,currentCountTemp);
            allCount.add(currentCountTemp);
            this.removeLastElement(currentCount);
        }
        else {
            int currentLength = part[idx]; //当前截取长度
            int currentMethod = left / currentLength; //当前可以截取几段
            //循环，可以选择不截取，或者最大currentMethod
            for(int partIdx=currentMethod;partIdx>=0;partIdx--) {
                System.out.println("idx: " + idx);
                System.out.println("partIdx: " + partIdx);
                System.out.println("left:" + left);
                currentCount.add(partIdx); //保存当前截取长度的截取数量
                int left2 = left - currentLength * partIdx; //截取后的剩余长度
                System.out.println("left2:" + left2);
                seperate(left2, idx + 1); //递归调用下一层
                this.removeLastElement(currentCount);//返回上一层之前 清空当前层的数据
            }
        }
    }

    private void copyList(List from, List to) {
        for (Object o : from) {
            to.add(o);
        }
    }

    private void removeLastElement(List list) {
        int len = list.size();
        if(len >= 1)
            list.remove(len-1);
    }
}
