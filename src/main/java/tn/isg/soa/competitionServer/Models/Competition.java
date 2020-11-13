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
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String country;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(mappedBy = "teamCompetitions")
    private Set<Team> teams=new HashSet<>();
}

