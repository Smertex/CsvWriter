package org.writer.model;

import by.smertex.annotation.Csv;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Csv(exclude = {"person"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestModel {
    private String username;

    private String firstname;

    private String lastname;

    private int age;

    private Person person;
}
