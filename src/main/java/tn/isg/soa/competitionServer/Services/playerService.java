package tn.isg.soa.competitionServer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.soa.competitionServer.Models.Player;
import tn.isg.soa.competitionServer.Repositories.playerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class playerService {

    private playerRepository playerRepos;

    @Autowired
    public playerService(playerRepository playerRepos) {
        this.playerRepos = playerRepos;
    }
    public ResponseEntity<?> addPlayer(Player p1)
    {
        List<Player> lst1=getPlayerByTshirtNumberAndTeam(p1.getTshirtNumber(),p1.getPlayerTeam().getName());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      Player player=playerRepos.save(p1);
      return new ResponseEntity<>(player,HttpStatus.CREATED);
    }

    private List<Player> getPlayerByTshirtNumberAndTeam(int tshirtNumber, String teamName) {
       return playerRepos.findAll().stream()
               .filter(x->x.getTshirtNumber()==tshirtNumber&&x.getPlayerTeam().getName().toLowerCase().equals(teamName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Player>> getAllPlayers()
    {
         List<Player> lst1=playerRepos.findAll();
         if(lst1.isEmpty())
             return new ResponseEntity(HttpStatus.NO_CONTENT);
         return new ResponseEntity(lst1,HttpStatus.OK);
    }
    public ResponseEntity<Player> getPlayerById(Long id)
    {
        Optional<Player> player=playerRepos.findById(id);
//        if(player.isEmpty())
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        return new ResponseEntity(player.get(),HttpStatus.OK);
        return player.map(x->ResponseEntity.ok().body(x))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
    public ResponseEntity<Player> updatePlayer(Long id,Player newPlayer)
    {
        Optional<Player> player=playerRepos.findById(id);
        if(player.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        List<Player> lst1=getPlayerByTshirtNumberAndTeam(newPlayer.getTshirtNumber(),newPlayer.getPlayerTeam().getName());
        if(!lst1.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Player player1=player.get();
        player1.setName(newPlayer.getName());
        player1.setTshirtNumber(newPlayer.getTshirtNumber());
        player1.setBirthDay(newPlayer.getBirthDay());
        player1.setPlayerTeam(newPlayer.getPlayerTeam());
        Player p1=playerRepos.save(player1);//save has double role 1-add new line if the id doesn't exist
                                                                //2-update an existing line if the id exist
        return new ResponseEntity(p1,HttpStatus.ACCEPTED);
    }
    public ResponseEntity deletePlayer(Long id)
    {
        Optional<Player> player=playerRepos.findById(id);
        if(player.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        playerRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
