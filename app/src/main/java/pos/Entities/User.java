package pos.Entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String surName;
    private String email;
    private Role role;
    private String password;
    private BigDecimal rating;
    private Boolean deleted;
}

