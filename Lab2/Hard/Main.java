//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MyLinkedHashSet<String> set = new MyLinkedHashSet<>();
        set.add("banana");
        set.add("apple");
        set.add("cherry");
        set.add("apple");
        System.out.println(set);

        System.out.println(set.contains("apple"));
        System.out.println("size = " + set.size());

        set.remove("apple");
        System.out.println(set);
        System.out.println("size = " + set.size());

        System.out.print("iterator: ");
        for (String s : set) System.out.print(s + " ");
        System.out.println();


        System.out.println("isEmpty = " + set.isEmpty());
        set.clear();
        System.out.println("after clear: " + set + ", isEmpty = " + set.isEmpty());
    }
}
