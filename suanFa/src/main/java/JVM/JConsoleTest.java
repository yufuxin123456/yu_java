package JVM;

import java.util.ArrayList;

/**
 * @author yufuxin
 * @date 2020/10/26 19:21
 */
public class JConsoleTest {

    private byte[] b1 = new byte[128*1024];

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("state.....");
        jj(1000);
    }

    private static void jj(int n) {
        ArrayList<JConsoleTest> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new JConsoleTest());
        }
    }
}
