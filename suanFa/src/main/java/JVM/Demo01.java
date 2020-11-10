package JVM;

import java.util.ArrayList;

/**
 * @author yufuxin
 * @date 2020/10/26 18:58
 */
public class Demo01 {
    public static void main(String[] args) {
        ArrayList<Demo> demos = new ArrayList<>();
        while (true){
            demos.add(new Demo());
        }
    }
}
