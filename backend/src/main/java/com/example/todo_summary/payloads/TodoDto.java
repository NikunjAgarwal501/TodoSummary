package com.example.todo_summary.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoDto {
    private Integer id;
    private String title;
}
