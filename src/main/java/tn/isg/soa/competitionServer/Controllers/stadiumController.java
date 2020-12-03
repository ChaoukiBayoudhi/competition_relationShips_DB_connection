package tn.isg.soa.competitionServer.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.isg.soa.competitionServer.Models.Stadium;
import tn.isg.soa.competitionServer.Services.stadiumService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stadiums")
public class stadiumController {
    @Autowired
    private stadiumService stdService;
    private final Logger log=LoggerFactory.getLogger(Stadium.class);
    @GetMapping("/all")
    public ResponseEntity<List<Stadium>> getAllstadiums()
    {
        return stdService.getAllStadiums();
    }

    @GetMapping("/stadium/{id}")
    public ResponseEntity<?> getStadiumById(@PathVariable Long id)
    {
        return stdService.getStadiumById(id);
    }
    @PostMapping("/newstadium")
    public ResponseEntity<Stadium> addNewStadium(@Valid @RequestBody Stadium st1)
    {
        return stdService.addStadium(st1);
    }
}
