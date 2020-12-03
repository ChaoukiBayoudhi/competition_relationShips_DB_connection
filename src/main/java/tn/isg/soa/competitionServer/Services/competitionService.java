package tn.isg.soa.competitionServer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.soa.competitionServer.Models.Competition;
import tn.isg.soa.competitionServer.Repositories.competitionRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class competitionService {

    private competitionRepository cmpRepos;

    @Autowired
    public competitionService(competitionRepository cmpRepos) {
        this.cmpRepos = cmpRepos;
    }

    public ResponseEntity<Competition> addCompetition(Competition c1)
    {
        List<Competition> lst1=findByNameAndDate(c1.getName(),c1.getStartDate());
        if(!lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        Competition competition=cmpRepos.save(c1);
        return new ResponseEntity(competition,HttpStatus.CREATED);
    }

    private List<Competition> findByNameAndDate(String name, LocalDateTime startDate) {
        return cmpRepos.findAll().stream()
                .filter(x->x.getName().toLowerCase().equals(name.toLowerCase()) && x.getStartDate().toLocalDate().isEqual(startDate.toLocalDate()))
                .collect(Collectors.toList());
    }


    public ResponseEntity<List<Competition>> getAllCompetitions()
    {
        List<Competition> lst1=cmpRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1,HttpStatus.OK);
    }
    public ResponseEntity<Competition> getCompetitionById(Long id)
    {
        Optional<Competition> result=cmpRepos.findById(id);
//        if(result.isEmpty())
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        return new ResponseEntity(result.get(),HttpStatus.OK);
        //or
       return result.map(x->ResponseEntity.ok().body(x))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity deleteCompetition(Long id)
    {
        Optional<Competition> result=cmpRepos.findById(id);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        cmpRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    public ResponseEntity<Competition> updateCompetition(Long id,Competition newCompetition)
    {
        Optional<Competition> result=cmpRepos.findById(id);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        List<Competition> lst1=findByNameAndDate(newCompetition.getName(),newCompetition.getStartDate());
        if(!lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        //save has double role : 1- add a new line if the id is a new one
        //2- update an existing line if the id is already affected
        Competition competition=result.get();
        competition.setName(newCompetition.getName());
        competition.setCountry(newCompetition.getCountry());
        competition.setStartDate(competition.getEndDate());
        competition.setEndDate(competition.getEndDate());
        competition.getTeams().clear();
        for (var x:newCompetition.getTeams()) {
            competition.getTeams().add(x);
        }
        Competition competition1=cmpRepos.save(competition);
        return new ResponseEntity(competition1,HttpStatus.ACCEPTED);
    }

}
