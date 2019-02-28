package com.ynhuang.websocket.demo1.search;

/**
 * @Auther: 018399
 * @Date: 2019/2/27 17:00
 * @Description:
 */
public class TwoFildSreach {

    public static void main(String[] args) {

        int[] arrs = new int[]{18,3,8,6,12,7};

        int search = search(arrs, 3);
        System.out.println(search);

    }

    /**
     *
     * @param value
     * @return
     */
    private static int search(int[] arrs, int value) {

        int start = 0;
        int end = arrs.length - 1;
        int mid;

        while(end >= start){
            mid = (end + start + 1) / 2;
            if(arrs[mid] == value){
                return mid;
            }else if (arrs[mid] > value){
                end = mid - 1;
            }else if(arrs[mid] < value){
                start = mid + 1;
            }else {
                return -1;
            }
        }
        return -1;
    }

}
