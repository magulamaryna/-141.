public class Person {

    public static int count = 0;
    public int health = 100;

    public Person() {
        count ++;
    }
    public static void GetCount() {
        System.out.println("Count:" + count);
    }
}
