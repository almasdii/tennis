package TableTennis.model;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Tag("unit")
public class GameTest {
    private Game game;

    @BeforeEach
    void setUp(){
        game = new Game();
    }
    private void winOneGame(PlayerNumber playerNumber){
        assertThat(game.pointWonBy(playerNumber)).isFalse();
        assertThat(game.pointWonBy(playerNumber)).isFalse();
        assertThat(game.pointWonBy(playerNumber)).isFalse();
        assertThat(game.pointWonBy(playerNumber)).isTrue();
    }

    @Test
    @DisplayName("Гейм заканчивается когда первый игрок выйгрывает четыре пойнта")
    void firstPlayerWinWhenGetScoreFourTimes(){
        winOneGame(PlayerNumber.FIRST_PLAYER);
        assertThat(game.isGameEnded()).isTrue();
    }

    @Test
    @DisplayName("Гейм заканчивается когда второй игрок выйгрывает четыре пойнта")
    void secondPlayerWinWhenGetScoreFourTimes(){
        winOneGame(PlayerNumber.SECOND_PLAYER);
        assertThat(game.isGameEnded()).isTrue();
    }

    @Test
    void scoreProgressesCorrectly(){
        assertThat(game.getFirstPlayerPoint()).isEqualTo(Point.LOVE);

        game.pointWonBy(PlayerNumber.FIRST_PLAYER);
        assertThat(game.getFirstPlayerPoint()).isEqualTo(Point.FIFTEEN);

        game.pointWonBy(PlayerNumber.FIRST_PLAYER);
        assertThat(game.getFirstPlayerPoint()).isEqualTo(Point.THIRTY);

        game.pointWonBy(PlayerNumber.FIRST_PLAYER);
        assertThat(game.getFirstPlayerPoint()).isEqualTo(Point.FORTY);
    }

    @Test
    @DisplayName("должен бросить исключения если после того как гейм закончился вызвался pointWonBy ")
    void throwExceptionWhenPointWinAfterFinished(){
        winOneGame(PlayerNumber.FIRST_PLAYER);
        assertThatThrownBy(()->game.pointWonBy(PlayerNumber.FIRST_PLAYER))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Game is already finished");
    }

    @Nested
    @DisplayName("Everything related with deuce")
    @Tag("Deuce logic")
    class Deuce{
        @BeforeEach
        void bringToDeuce(){
            for (int i = 0; i < 3; i++) {
                assertThat(game.pointWonBy(PlayerNumber.SECOND_PLAYER)).isFalse();
                assertThat(game.pointWonBy(PlayerNumber.FIRST_PLAYER)).isFalse();
            }
        }

        @Test
        void deuceRestoredWhenOpponentEqualizes() {
            game.pointWonBy(PlayerNumber.FIRST_PLAYER);
            game.pointWonBy(PlayerNumber.SECOND_PLAYER);

            assertThat(game.isDeuce()).isTrue();
            assertThat(game.pointWonBy(PlayerNumber.FIRST_PLAYER)).isFalse();
        }

        @Test
        void deuceShouldStartWhenScoresForty(){
            assertThat(game.getFirstPlayerPoint()).isEqualTo(Point.FORTY);
            assertThat(game.getSecondPlayerPoint()).isEqualTo(Point.FORTY);
            assertThat(game.isDeuce()).isTrue();
        }
        @Test
        void playerShouldWinInDeuceAfterWinTwice(){
            assertThat(game.pointWonBy(PlayerNumber.FIRST_PLAYER)).isFalse();
            assertThat(game.pointWonBy(PlayerNumber.FIRST_PLAYER)).isTrue();
        }
        @Test
        void advantagePlayerOneAfterDeuce(){
            game.pointWonBy(PlayerNumber.FIRST_PLAYER);
            assertThat(game.getAdvantage()).isEqualTo(PlayerNumber.FIRST_PLAYER);
        }

    }
}
