package tn.isg.soa.competitionServer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.soa.competitionServer.Models.Competition;
import tn.isg.soa.competitionServer.Models.Statistic;
import tn.isg.soa.competitionServer.Repositories.competitionRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public ResponseEntity<?> addCompetition(Competition c1)
    {
        List<Competition> result = findByNameAndDate(c1.getName(),c1.getStartDate().toLocalDate());
        if(!result.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        Competition competition=cmpRepos.save(c1);
        return new ResponseEntity<>(competition,HttpStatus.CREATED);
    }

    private List<Competition> findByNameAndDate(String name, LocalDate startDate) {
        return cmpRepos.findAll().stream()
                .filter(x->x.getName().equalsIgnoreCase(name)&&x.getStartDate().toLocalDate().isEqual(startDate))
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Competition>> getAllCompetitions()
    {
        List<Competition> lst1=cmpRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1,HttpStatus.OK);
    }
    public ResponseEntity<?> getCompetitionById(Long id)
    {
        Optional<Competition> result= cmpRepos.findById(id);
//        if(result.isEmpty())
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(result.get(),HttpStatus.OK);
   return result.map(x->ResponseEntity.ok().body(x))
           .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity deleteCompetition(Long id)
    {
        Optional<Competition> result= cmpRepos.findById(id);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        cmpRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    public ResponseEntity<Competition> updateCompetition(Long id, Competition newCompetition)
    {
        Optional<Competition> result= cmpRepos.findById(id);
        if(result.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Competition competition= result.get();
        competition.setName(newCompetition.getName());
        competition.setCountry(newCompetition.getCountry());
        competition.setStartDate(competition.getStartDate());
        competition.setEndDate(competition.getEndDate());
        Competition competition1=cmpRepos.save(competition);
        return new ResponseEntity(competition,HttpStatus.ACCEPTED);
    }
    public ResponseEntity<List<Statistic>> getStatistic(String companyName)
    {
        List<Competition> lst1=cmpRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        List<Competition> lst2=lst1.stream()
        .filter(c -> c.getName().equalsIgnoreCase(companyName))
        .collect(Collectors.toList());
        if(lst2.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        List<Statistic> lstStats=new ArrayList<Statistic>();
        for (Competition x:lst2) {
            lstStats.add(new Statistic(x.getStartDate().getYear(),x.getTotalGoals()));
        }
        return new ResponseEntity(lstStats,HttpStatus.OK);
    }

}
