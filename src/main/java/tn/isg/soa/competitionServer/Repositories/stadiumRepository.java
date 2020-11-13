package tn.isg.soa.competitionServer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.isg.soa.competitionServer.Models.Stadium;
@RepositoryRestResource
public interface stadiumRepository extends JpaRepository<Stadium,Long> {
}
