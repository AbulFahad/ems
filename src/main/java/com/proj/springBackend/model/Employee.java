package com.proj.springBackend.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
