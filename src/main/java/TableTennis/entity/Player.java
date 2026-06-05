package TableTennis.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@ToString
@Table(name = "players")
@Entity
public class Player extends BaseEntity<Long>{

    private String name;
    public Player(String name){
        this.name = name;
    }
}
