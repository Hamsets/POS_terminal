package pos.Entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pos {
    private int posId;
    private String publicName;
    private String address;
//    private ArrayList<User> usersIds;
    private Boolean deleted;
}
