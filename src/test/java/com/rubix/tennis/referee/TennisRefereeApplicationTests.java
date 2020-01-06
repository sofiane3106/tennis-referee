package com.rubix.tennis.referee;

import com.rubix.tennis.referee.domain.Game;
import com.rubix.tennis.referee.domain.Player;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
class TennisRefereeApplicationTests {

    //@Inject HelloService helloService;

    @DisplayName("Test Spring @Autowired Integration")
    @Test
    void testGet() {
        Player playerOne = Player.builder().firstName("Sofiane").lastName("REBIB").build();
        Player playerTwo = Player.builder().firstName("Dupont").lastName("LORELLA").build();

        Game game = Game.builder().playerOne(playerOne).playerTwo(playerTwo).build();

        log.debug(game.toString());
    }

}
