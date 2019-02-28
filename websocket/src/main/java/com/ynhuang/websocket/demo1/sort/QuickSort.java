package com.ynhuang.websocket.demo1.sort;

/**
 * @Auther: 018399
 * @Date: 2019/2/27 16:11
 * @Description:
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arrs = new int[]{18,3,8,6,12,7};
        sort(arrs, 0, arrs.length - 1);
        System.out.println(arrs[0] + "," + arrs[1] + "," + arrs[2] + "," + arrs[3] + "," + arrs[4] + "," + arrs[5] + ",");

    }

    /**
     *
     * @param arrs
     * @param s : point
     * @param e
     */
    private static void sort(int[] arrs, int s, int e) {

        int start = s;
        int end = e;
        int pointValue = arrs[s];

        while(end > start){

            while(end > start && arrs[end] >= pointValue){
                end--;
            }

            if(arrs[end] < pointValue){
                int temp = arrs[end];
                arrs[end] = arrs[start];
                arrs[start] = temp;
            }

            while(end > start && arrs[start] <= pointValue){
                start++;
            }

            if(arrs[start] > pointValue){
                int temp = arrs[start];
                arrs[start] = arrs[end];
                arrs[end] = temp;
            }


        }

        if(start > s){
            sort(arrs, s, start-1);
        }
        if(end < e){
            sort(arrs, end + 1, e);
        }

    }

}
