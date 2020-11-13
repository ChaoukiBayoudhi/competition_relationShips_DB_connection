package tn.isg.soa.competitionServer.Models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
//@AllArgsConstructor
//or @Data
@Entity
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private int capacity;
    @OneToOne
    @JoinColumn(name="team_id", referencedColumnName = "id")
    private Team teamOwner;

}
