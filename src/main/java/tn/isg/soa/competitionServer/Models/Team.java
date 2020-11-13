package tn.isg.soa.competitionServer.Models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
//@AllArgsConstructor
//or @Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private int nbPlayers;
    private LocalDate creationDate;
    @OneToMany(mappedBy="playerTeam",cascade=CascadeType.ALL)
    private Set<Player> teamPlayers=new HashSet<>();
    @OneToOne (mappedBy="teamOwner")
    private Stadium teamStadium;
    @ManyToMany
    @JoinTable(name="team_competition",
            joinColumns=@JoinColumn(name="team_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="competition_id",referencedColumnName="id")
    )
    private Set<Competition> teamCompetitions =new HashSet<>();
}
