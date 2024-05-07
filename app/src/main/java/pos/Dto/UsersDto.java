package pos.Dto;

import java.math.BigDecimal;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UsersDto{
    private int id;
    private String firstName;
    private String lastName;
    private String surName;
    private String email;
    private Role role;
    private String password;
    private BigDecimal rating;
    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDto usersDto = (UsersDto) o;
        return id == usersDto.id
                && firstName.equals(usersDto.firstName)
                && lastName.equals(usersDto.lastName)
                && surName.equals(usersDto.surName)
                && email.equals(usersDto.email)
                && role.equals(usersDto.role)
                && password.equals(usersDto.password)
                && rating.equals(usersDto.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, surName, email, role, password, rating);
    }
}
