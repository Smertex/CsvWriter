package by.smertex.test.writer;

import by.smertex.annotation.Csv;
import by.smertex.writer.interfaces.ModelManager;
import by.smertex.util.realisation.ClassFinderBasicRealisation;
import by.smertex.writer.realisation.ModelManagerBasicRealisation;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ModelManagerTest {

    private static final String BASIC_PATH = "org.writer";

    private final ModelManager modelManager = new ModelManagerBasicRealisation(BASIC_PATH);

    @Test
    void findModelsTest(){
        List<Class<?>> models = new ClassFinderBasicRealisation(BASIC_PATH).getClasses().stream()
                .filter(clazz -> clazz.isAnnotationPresent(Csv.class))
                .toList();
        modelManager.getModels().keySet().forEach(key -> {
            assert models.contains(key);
        });
    }
}
