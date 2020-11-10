package 排序;

/**
 * @author yufuxin
 * @date 2020/10/21 9:18
 */
public class 冒泡排序 {
    public static void main(String[] args) {
        int[] x ={1,4,13,3,15,12,17,46,9,6};

        for (int i = 0; i < x.length; i++) {
            for (int i1 = 0; i1 < x.length-1; i1++) {
                if (x[i1]>x[i1+1]){
                    int y = x[i1];
                    x[i1] = x[i1+1];
                    x[i1+1] = y;
                }
            }
        }

        for (int i : x) {
            System.out.println(i);
        }

    }
}
