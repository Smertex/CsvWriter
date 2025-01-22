package org.writer.model;

import by.smertex.annotation.Csv;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Csv
public class Person {

    private String firstName;

    private String lastName;

    private int dayOfBirth;

    private Months monthOfBirth;

    private int yearOfBirth;
}
