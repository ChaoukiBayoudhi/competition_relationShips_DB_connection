package tn.isg.soa.competitionServer.Controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.isg.soa.competitionServer.Models.Competition;
import tn.isg.soa.competitionServer.Models.Statistic;
import tn.isg.soa.competitionServer.Services.competitionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/competitions")
public class competitionController {
    @Autowired
    private competitionService cmpService;

    private final Logger log = LoggerFactory.getLogger(competitionController.class);
    @GetMapping("/all")
    public ResponseEntity<List<Competition>>getAllCompetitions()
    {
        return cmpService.getAllCompetitions();
    }
    @GetMapping("/compettion/{id}")
    public ResponseEntity<?> getCompetitionById(@PathVariable Long id)
    {
        return cmpService.getCompetitionById(id);
    }
    @GetMapping("/statistics/{competitionName}")
    public ResponseEntity<List<Statistic>>getStatistic(@PathVariable String competitionName)
    {
        return cmpService.getStatistic(competitionName);
    }

    @PostMapping("/newcompetition")
    public ResponseEntity<?> addNewCompetition(@Valid @RequestBody Competition c1)
    {
        log.info("Request for adding new Competition",c1);
        return cmpService.addCompetition(c1);
    }
    @PutMapping("/competition/{id}")
    public ResponseEntity<Competition> modifyCompetition(@PathVariable Long id, @Valid @RequestBody Competition c1)
    {
        log.info("Request for modifying new Competition",c1);
        return cmpService.updateCompetition(id,c1);
    }
    @DeleteMapping("/competition/{id}")
    public ResponseEntity removeCompetition(@PathVariable Long id)
    {
        log.info("Request for deleting new Competition",id);
        return cmpService.deleteCompetition(id);
    }

}
