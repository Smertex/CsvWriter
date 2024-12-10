package org.writer;

import by.smertex.writer.interfaces.Writable;
import by.smertex.writer.realisation.WritableBasicRealisation;
import net.datafaker.Faker;
import org.writer.model.Months;
import org.writer.model.Person;
import org.writer.model.Student;
import org.writer.model.TestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        personSave();
        studentSave();
        testModelSave();
    }

    //Пример сохранения person
    private static void personSave(){
        String path = "TestPerson.csv";
        by.smertex.writer.interfaces.Writable writable = new WritableBasicRealisation("org.writer.model");

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

        writable.writeToFile(data, path);
    }

    //Пример сохранения student
    private static void studentSave(){
        String path = "TestStudent.csv";
        by.smertex.writer.interfaces.Writable writable = new WritableBasicRealisation("org.writer.model");

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

        writable.writeToFile(data, path);
    }

    //Пример сохранения testModel
    private static void testModelSave(){
        String path = "TestTestModel.csv";
        Writable writable = new WritableBasicRealisation("org.writer.model");

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

        writable.writeToFile(data, path);
    }
}