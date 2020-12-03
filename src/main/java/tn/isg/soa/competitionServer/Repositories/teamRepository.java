package tn.isg.soa.competitionServer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.isg.soa.competitionServer.Models.Team;

import java.util.Optional;

@RepositoryRestResource
public interface teamRepository extends JpaRepository<Team,Long> {

    @Query("FROM Team WHERE name = ?1")
    public Optional<Team> findByName(String name);
}
