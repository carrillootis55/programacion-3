package utils;

public class TestHash {
    public static void main(String[] args) {
        String hash = PasswordUtils.hashPassword("3267");
        System.out.println(hash);
    }
}
