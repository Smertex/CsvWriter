package by.smertex.test.writer;

import by.smertex.exception.writer.DataValidationException;
import by.smertex.util.interfaces.Mapper;
import by.smertex.writer.realisation.ModelManagerBasicRealisation;
import by.smertex.util.realisation.ModelToCsvStringMapper;
import by.smertex.writer.interfaces.Writable;
import by.smertex.writer.realisation.WritableBasicRealisation;
import org.junit.jupiter.api.Test;

import net.datafaker.Faker;
import org.writer.model.Months;
import org.writer.model.Person;
import org.writer.model.Student;
import org.writer.model.TestModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class WritableTest {

    private static final String BASIC_PATH = "Test.csv";

    public final Writable writable = new WritableBasicRealisation("org.writer.model");

    public final Mapper<Object, String> mapper = new ModelToCsvStringMapper(new ModelManagerBasicRealisation("org.writer.model"));

    @Test
    void writePersonTest() {
        Faker faker = new Faker();
        Months[] months = Months.values();
        List<Person> data = new ArrayList<>();
        Random random = new Random();

        IntStream.range(0, 20)
                .forEach(i -> {
                    Person person = Person.builder()
                            .firstName(faker.name().firstName())
                            .lastName(faker.name().lastName())
                            .dayOfBirth(random.nextInt(28) + 1)
                            .monthOfBirth(months[random.nextInt(12)])
                            .yearOfBirth(random.nextInt(84) + 1940)
                            .build();
                    data.add(person);
                });

        writable.writeToFile(data, BASIC_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(BASIC_PATH))) {
            List<String> readCsv = reader.lines().toList();
            assert readCsv.size() == data.size();

            IntStream.range(0, readCsv.size())
                    .forEach(i -> {
                        assert readCsv.get(i).equals(mapper.map(data.get(i)));
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void writeStudentTest(){
        Faker faker = new Faker();
        List<Student> data = new ArrayList<>();
        Random random = new Random();

        IntStream.range(0, 20)
                .forEach(i -> {
                    Student student = new Student(faker.name().firstName(), new ArrayList<>());
                    List<String> score = new ArrayList<>();
                    student.setScore(score);
                    IntStream.range(0, random.nextInt(10) + 5)
                            .forEach(j -> score.add((String.valueOf(random.nextInt(100))))
                            );
                    data.add(student);
                });

        writable.writeToFile(data, BASIC_PATH);

        try(BufferedReader reader = new BufferedReader(new FileReader(BASIC_PATH))){
            List<String> readCsv = reader.lines().toList();
            assert readCsv.size() == data.size();

            IntStream.range(0, readCsv.size())
                    .forEach(i -> {
                        assert readCsv.get(i).equals(mapper.map(data.get(i)));
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void tryNotModelWriteTest(){
        List<Object> list = new ArrayList<>();
        list.add(new Object());

        try{
            writable.writeToFile(list, BASIC_PATH);
            assert false;
        } catch (DataValidationException e){
            assert true;
        }
    }

    @Test
    void writeWhereInsertedObjectTest(){
        Faker faker = new Faker();
        Random random = new Random();
        Months[] months = Months.values();
        Person person = Person.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .dayOfBirth(random.nextInt(28) + 1)
                .monthOfBirth(months[random.nextInt(12)])
                .yearOfBirth(random.nextInt(84) + 1940)
                .build();
        TestModel testModel = TestModel.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .username(faker.name().username())
                .age(random.nextInt(100))
                .person(person)
                .build();
        List<TestModel> data = new ArrayList<>();
        data.add(testModel);

        writable.writeToFile(data, BASIC_PATH);
    }

}
