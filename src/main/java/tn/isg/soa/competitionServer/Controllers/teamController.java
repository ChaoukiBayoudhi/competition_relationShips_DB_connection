package tn.isg.soa.competitionServer.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.isg.soa.competitionServer.Models.Team;
import tn.isg.soa.competitionServer.Services.teamService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class teamController {
    @Autowired
    private teamService teamServ;
    private final Logger log= LoggerFactory.getLogger(Team.class);
    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeams()
    {
        return teamServ.getAllTeams();
    }
    @GetMapping("/team/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id)
    {
        return teamServ.getOneTeam(id);
    }
    @PostMapping("/newteam")
    public ResponseEntity<Team> addNewTeam(@Valid @RequestBody Team t1)
    {
        return teamServ.addTeam(t1);
    }

}
