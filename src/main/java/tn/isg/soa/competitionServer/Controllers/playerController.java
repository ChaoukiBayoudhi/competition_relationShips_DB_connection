package tn.isg.soa.competitionServer.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.isg.soa.competitionServer.Models.Player;
import tn.isg.soa.competitionServer.Services.playerService;

import javax.validation.Valid;

@RequestMapping("/players")
@RestController
public class playerController {
    @Autowired
    private playerService playerServ;

    private final Logger log= LoggerFactory.getLogger(playerController.class);

    @GetMapping("/all")
    public ResponseEntity<?> getAllPlayers()
    {
        return playerServ.getAllPlayers();
    }
//    @GetMapping("/allOrByName")
//    public ResponseEntity<List<Player>> getAllPlayersOrByName(@RequestParam(required = false) String name) {
//        try {
//            List<Player> Players = new ArrayList<>();
//
//            if (name == null)
//                playerServ.findAll().forEach(Players::add);
//            else
//                playerServ.findByName(name).forEach(Players::add);
//
//            if (Players.isEmpty()) {
//                return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(Players, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/player/{id}")
    public ResponseEntity<?> getPlayerById(@PathVariable("id") long id) {
        return playerServ.getPlayerById(id);
        }

    @PostMapping("/player")
    public ResponseEntity<?> createPlayer(@Valid @RequestBody Player player) {

            log.info("Request for creating Player {}",player);
            return playerServ.addPlayer(player);
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable("id") long id,@Valid @RequestBody Player player) {
        log.info("Request for updating Player {}",id);
        return playerServ.updatePlayer(id,player);
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable("id") long id) {

            log.info("Request for removing Player {}",id);
           return playerServ.deletePlayer(id);

    }

//    @DeleteMapping("/players")
//    public ResponseEntity<HttpStatus> deleteAllPlayers() {
//        try {
//            log.info("Request for removing all Players");
//
//            playerServ.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

//       @GetMapping("/players/published")
//    public ResponseEntity<List<Player>> findByPublished() {
//        try {
//            List<Player> Players = playerServ.findByName(name);
//
//            if (Players.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(Players, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
