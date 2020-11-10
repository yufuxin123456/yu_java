package 排序;

/**
 * @author yufuxin
 * @date 2020/10/21 9:30
 */
public class 选择排序 {
    public static void main(String[] args) {
        int[] arr = {1, 4, 13, 3, 15, 12, 17, 46, 9, 6};
        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }

        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
