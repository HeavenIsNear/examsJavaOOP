package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field{
    private Collection<Player> players;

    @Override
    public String start(Collection<Player> players) {

        List <Player> terrorists = new ArrayList<>(getListOfCorrectPlayers(players, "Terrorist"));

        List <Player> counterTerrorists = new ArrayList<>(getListOfCorrectPlayers(players, "CounterTerrorist"));

        while (terrorists.size() > 0 && counterTerrorists.size() > 0) {
            attack(terrorists, counterTerrorists);
            attack(counterTerrorists, terrorists);
        }

        return terrorists.isEmpty() ? OutputMessages.COUNTER_TERRORIST_WINS : OutputMessages.TERRORIST_WINS;
    }

    private void attack(List<Player> teamOne, List<Player> teamTwo) {
        for (Player playerOne : teamOne) {
            int damageDone = playerOne.getGun().fire();

            for (Player playerTwo : teamTwo) {
                playerTwo.takeDamage(damageDone);
            }

            teamTwo.removeIf(player -> !player.isAlive());
        }
    }

    private List<Player> getListOfCorrectPlayers(Collection<Player> players, String type) {
        return players.stream()
                .filter(p -> p.getClass().getSimpleName().equals(type))
                .collect(Collectors.toList());
    }
}
