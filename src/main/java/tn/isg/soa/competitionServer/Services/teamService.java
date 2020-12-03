package tn.isg.soa.competitionServer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.soa.competitionServer.Models.Team;
import tn.isg.soa.competitionServer.Repositories.teamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class teamService {
    @Autowired
    private teamRepository teamRepos;

    public ResponseEntity<Team> addTeam(Team t1)
    {
        Optional<Team> res=teamRepos.findByName(t1.getName());
        if(res.isPresent())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Team t2=teamRepos.save(t1);
        return new ResponseEntity(t2,HttpStatus.CREATED);
    }

    public ResponseEntity<List<Team>> getAllTeams()
    {
        List<Team> lst1=teamRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1,HttpStatus.OK);

    }
    public ResponseEntity<?> getOneTeam(Long id)
    {
        Optional<Team> team=teamRepos.findById(id);
//        if(team.isEmpty())
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        return new ResponseEntity(team,HttpStatus.OK);
        //or
        return team.map(x->ResponseEntity.ok().body(team))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity deteleTeam(Long id)
    {
        Optional<Team> team=teamRepos.findById(id);
        if(team.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        teamRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    public ResponseEntity<Team> updateTeam(Long id, Team newTeam)
    {
        Optional<Team> team=teamRepos.findById(id);
        if(team.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Team team1=team.get();
        team1.setName(newTeam.getName());
        team1.setCreationDate(newTeam.getCreationDate());
        team1.setNbPlayers(newTeam.getNbPlayers());
        Team t1=teamRepos.save(team1);
        return new ResponseEntity(t1,HttpStatus.OK);

    }
}
