package tn.isg.soa.competitionServer;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.isg.soa.competitionServer.Models.Player;
import tn.isg.soa.competitionServer.Repositories.playerRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

@SpringBootApplication
public class CompetitionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetitionServerApplication.class, args);
	}

//	@Bean
//	ApplicationRunner initPlayers(playerRepository plRepos)
//	{
//		return x ->{
//			Stream.of(
//					new Player("Ronaldo", LocalDate.of(1984, Month.FEBRUARY,12),7),
//					new Player("Messi", LocalDate.of(1984, Month.FEBRUARY,12),10)
//			).forEach(plRepos::save);
//		} ;
//	}

}
