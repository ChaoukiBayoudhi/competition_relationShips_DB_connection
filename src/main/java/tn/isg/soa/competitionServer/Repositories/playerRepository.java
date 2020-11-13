package tn.isg.soa.competitionServer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import tn.isg.soa.competitionServer.Models.Player;

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
}
