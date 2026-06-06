package TableTennis.service;

import TableTennis.dao.PlayerDao;
import TableTennis.entity.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
class OngoingMatchesServiceTest {

    @Mock
    private PlayerDao playerDao;
    @Mock
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    @InjectMocks
    private OngoingMatchesService ongoingMatchesService;

    @Test
    @Disabled
    void returnsExistingPlayerByName() {
        Player player = new Player("Almas");

        doReturn(Optional.of(player)).when(playerDao).findByName("Almas");

//        Optional<Player> almas = ongoingMatchesService.("Almas");
//        assertThat(almas).isPresent().contains(player);
    }

    @Test
    void returnAllWhenFindAll() {

    }
}
