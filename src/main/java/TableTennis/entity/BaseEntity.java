package TableTennis.entity;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
public class BaseEntity<K> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private K id;

    @Version
    private Long version;
}
