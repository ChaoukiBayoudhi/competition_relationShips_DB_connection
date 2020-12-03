package tn.isg.soa.competitionServer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import tn.isg.soa.competitionServer.Models.Stadium;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
//@Repository
public interface stadiumRepository extends JpaRepository<Stadium,Long> {
//    @Query("From Stadium where name = ?1") //JPQL or HQL
//    public Optional<Stadium> findByName(String name);








    //    @Query("select s From Stadium s where s.adress Like %:adress%")
//    public List<Stadium> getStadiumByAddress(String adress);
}
