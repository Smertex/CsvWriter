package by.smertex.test.util;

import by.smertex.util.interfaces.Mapper;
import by.smertex.writer.interfaces.ModelManager;
import by.smertex.writer.realisation.ModelManagerBasicRealisation;
import by.smertex.util.realisation.ModelToCsvStringMapper;
import org.junit.jupiter.api.Test;
import org.writer.model.Months;
import org.writer.model.Person;
import org.writer.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MapperTest {

    private static final String BASIC_PATH = "org.writer";

    private final ModelManager modelManager = new ModelManagerBasicRealisation(BASIC_PATH);

    private final Mapper<Object, String> mapper = new ModelToCsvStringMapper(modelManager);

    @Test
    void personMapTest(){
        Person person = new Person("John", "Smith", 27, Months.DECEMBER, 1990);
        assert mapper.map(person).equals("John,Smith,27,DECEMBER,1990");
    }

    @Test
    void studentMapTest(){
        List<String> score = new ArrayList<>();
        score.add("70");
        score.add("30");
        score.add("20");
        Student student = new Student("Anton", score);
        assert mapper.map(student).equals("Anton,70,30,20");
    }
}
