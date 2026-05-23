package TableTennis.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
@Builder
@Table(name = "matches")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "player1")
    private int firstPlayerId;
    @Column(name = "player2")
    private int secondPlayerId;
    @Column(name = "winner")
    private int winnerId;
    public MatchEntity(int firstPlayerId,int secondPlayerId,int winnerId){
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        this.winnerId = winnerId;
    }
}


