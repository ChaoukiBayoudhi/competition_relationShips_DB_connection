package tn.isg.soa.competitionServer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.isg.soa.competitionServer.Models.Stadium;
import tn.isg.soa.competitionServer.Repositories.stadiumRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class stadiumService {

    private stadiumRepository stadiumRepos;

    @Autowired
    public stadiumService(stadiumRepository stadiumRepos) {
        this.stadiumRepos = stadiumRepos;
    }
    public ResponseEntity<Stadium> addStadium(Stadium std1)
    {
        List<Stadium> lst1=findByName(std1.getName());
        if(lst1.isEmpty()) {
            Stadium st1=stadiumRepos.save(std1);
            return new ResponseEntity(st1, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    private List<Stadium> findByName(String name) {
        return stadiumRepos.findAll().stream()
                .filter(y ->y.getName().toLowerCase().equals(name.toLowerCase()))
                .collect(Collectors.toList());

    }

    public ResponseEntity<List<Stadium>> getAllStadiums()
    {
        List<Stadium> lst1=stadiumRepos.findAll();
        if(lst1.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(lst1,HttpStatus.OK);
    }
    public ResponseEntity<?> getStadiumById(Long id)
    {
        Optional<Stadium> stadium=stadiumRepos.findById(id);
//        if(stadium.isEmpty())
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        return new ResponseEntity(stadium,HttpStatus.OK);
        //or
        return stadium.map(x->ResponseEntity.ok().body(x))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));

    }
    public ResponseEntity deleteStadiumById(Long id)
    {
        Optional<Stadium> stadium=stadiumRepos.findById(id);
        if(stadium.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        stadiumRepos.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    public ResponseEntity<Stadium> updateStadium(Long id,Stadium newStadium)
    {
        Optional<Stadium> stadium=stadiumRepos.findById(id);
        if(stadium.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Stadium stadium1=stadium.get();
        stadium1.setName(newStadium.getName());
        stadium1.setCapacity(newStadium.getCapacity());
        stadium1.setTeamOwner(newStadium.getTeamOwner());
        Stadium stadium2 = stadiumRepos.save(stadium1);
        return new ResponseEntity(stadium2,HttpStatus.OK);
    }

}
