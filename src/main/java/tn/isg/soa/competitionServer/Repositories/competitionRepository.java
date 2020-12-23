package tn.isg.soa.competitionServer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import tn.isg.soa.competitionServer.Models.Competition;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface competitionRepository extends JpaRepository<Competition,Long> {


    //JPQL or HQL

    //   @Query(value = "select c From Competition c where c.name= ?1 and c.startDate= ?2" ) //JPQL or HQL     //      ,nativeQuery = true)
//    List<Competition> findByNameAndDate(String name, LocalDate dt);
}
