package tn.isg.soa.competitionServer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import tn.isg.soa.competitionServer.Models.Player;

import java.time.LocalDate;
import java.util.List;

//@Repository
//ou bien
@RepositoryRestResource
public interface playerRepository extends JpaRepository<Player,Long> {
    //JpaRepository interface
    //T save(T obj): pour sauvegarder un objet dans la base de données
    //Delete(T obj)
    //List<T> findAll()
    //T findById(S id)
    //....
//    @Query("Select p from player p where p.tshirtNumber=?1 and idTeam=?2")
//    List<Player> getPlayerByTshirtNumberAndTeam(int tshirtNumber,Long idTeam);

//   @Query("FROM Player WHERE name = ?1 and birthday= ?2") //JPQL ou HQL
//    List<Player> getPlayerByNameAndBirthDay(String name, LocalDate birthDay);

    List<Player> findByNameContaining(String ch);

}
