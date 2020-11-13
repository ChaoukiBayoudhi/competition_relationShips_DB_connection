package tn.isg.soa.competitionServer.Models;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
//@AllArgsConstructor
//or @Data
@Entity
@Table(name = "player_tab")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
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

    public Player(String name, int tshirtNumber, LocalDate birthDay, String position, byte[] photo) {
        this.name = name;
        this.tshirtNumber = tshirtNumber;
        this.birthDay = birthDay;
        this.position = position;
        this.photo = photo;
    }


}
