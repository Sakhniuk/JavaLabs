import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HotClassLoader extends ClassLoader {

    private final Path classesDir;
    private final String targetClassName;

    public HotClassLoader(Path classesDir, String targetClassName) {
        super(HotClassLoader.class.getClassLoader());
        this.classesDir = classesDir;
        this.targetClassName = targetClassName;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (targetClassName.equals(name)) {
            Class<?> c = findClass(name);
            if (resolve) resolveClass(c);
            return c;
        }

        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path classFile = classesDir.resolve(name.replace('.', '/') + ".class");
        try {
            byte[] bytes = Files.readAllBytes(classFile);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Не знайдено: " + classFile, e);
        }
    }
}