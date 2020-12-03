package tn.isg.soa.competitionServer.Models;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
//or @Data
@NoArgsConstructor
@RequiredArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "player_tab")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    //@NonNull
    private LocalDate birthDay;
    @NonNull
    private int tshirtNumber;

    private String position;
    @Lob
    private byte[] photo;
    @ManyToOne
    @JoinColumn(name="team_id", referencedColumnName="id")
    //En SQL
    //constraint fk1 foreign key team_id references Team(id)
    private Team playerTeam;

    public Player(@NonNull String name, @NonNull LocalDate birthDay, @NonNull int tshirtNumber, String position, byte[] photo, Team playerTeam) {
        this.name = name;
        this.birthDay = birthDay;
        this.tshirtNumber = tshirtNumber;
        this.position = position;
        this.photo = photo;
        this.playerTeam = playerTeam;
    }
}
