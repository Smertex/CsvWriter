package by.smertex.test.writer;

import by.smertex.annotation.Csv;
import by.smertex.writer.interfaces.ModelManager;
import by.smertex.writer.interfaces.ModelMetadata;
import by.smertex.writer.realisation.ModelManagerBasicRealisation;
import org.junit.jupiter.api.Test;
import org.writer.model.TestModel;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ModelMetadataTest {

    private static final String BASIC_PATH = "org.writer";

    private final ModelManager modelManager = new ModelManagerBasicRealisation(BASIC_PATH);

    @Test
    void fieldsModelMetadataTest() {
        ModelMetadata modelMetadata = modelManager.getModelMetadata(TestModel.class);
        List<Field> fields = Arrays.stream(TestModel.class.getDeclaredFields()).toList();

        if(TestModel.class.getDeclaredAnnotation(Csv.class).exclude().length == 0)
            assert modelMetadata.getFields().size() == fields.size();
        else
            assert !(modelMetadata.getFields().size() == fields.size());
    }

}
