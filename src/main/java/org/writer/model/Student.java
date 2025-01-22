package org.writer.model;

import by.smertex.annotation.Csv;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Csv
public class Student {

    private String name;

    private List<String> score;
}