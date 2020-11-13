package tn.isg.soa.competitionServer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import tn.isg.soa.competitionServer.Models.Competition;

@Repository
public interface competitionRepository extends JpaRepository<Competition,Long> {

    //cretate connection...
    //request=insert into Competition .....
}
