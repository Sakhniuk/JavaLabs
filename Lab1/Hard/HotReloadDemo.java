import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

public class HotReloadDemo {

    private static final Path SOURCE = Paths.get("TestModule.java");
    private static final Path CLASSES_DIR = Paths.get("build", "hot");

    public static void main(String[] args) throws Exception {
        compile();
        printInstance();

        FileTime lastModified = Files.getLastModifiedTime(SOURCE);
        System.out.println(">>> Очікування змін у " + SOURCE + " ...\n");

        while (true) {
            Thread.sleep(500);
            FileTime current = Files.getLastModifiedTime(SOURCE);
            if (current.compareTo(lastModified) > 0) {
                System.out.println(" Виявлено зміни. Перекомпіляція... ");
                if (compile()) {
                    printInstance();
                } else {
                    System.err.println("!!! Помилка компіляції.");
                }
                lastModified = current;
                System.out.println(">>> Очікування змін ...\n");
            }
        }
    }

    private static void printInstance() throws Exception {
        HotClassLoader loader = new HotClassLoader(CLASSES_DIR, "TestModule");
        Class<?> clazz = loader.loadClass("TestModule");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        System.out.println(instance);
    }


    private static boolean compile() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.err.println("Немає JavaCompiler. Запускайте на JDK, а не JRE.");
            return false;
        }

        try {
            Files.createDirectories(CLASSES_DIR);
        } catch (IOException e) {
            System.err.println("Не вдалося створити " + CLASSES_DIR + ": " + e.getMessage());
            return false;
        }

        int result = compiler.run(null, null, null,
                "-d", CLASSES_DIR.toString(),
                SOURCE.toString());
        return result == 0;
    }
}
