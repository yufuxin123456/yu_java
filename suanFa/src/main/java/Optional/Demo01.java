package Optional;

import java.util.Optional;

/**
 * @author yufuxin
 * @date 2020/10/26 8:56
 */
public class Demo01 {
    public static void main(String[] args) {
        User user = new User();
        Optional<User> emptyOpt = Optional.of(user);

        //aa();

        bb();

    }

    private static void bb() {
        Optional<User> p = Optional.ofNullable(new User());

        User u = p.get();
        System.out.println(u);
    }

    private static void aa() {
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);

        Optional<byte[]> bytes = opt.map(String::getBytes);
    }


}
