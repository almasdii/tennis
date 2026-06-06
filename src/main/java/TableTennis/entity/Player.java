package TableTennis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@ToString
@Table(name = "players")
@Entity
public class Player extends BaseEntity<Long>{

    @Column(name = "name",unique = true,nullable = false)
    @NotBlank
    @Size(min = 1,max = 20)
    private String name;
    public Player(String name){
        this.name = name;
    }
}
