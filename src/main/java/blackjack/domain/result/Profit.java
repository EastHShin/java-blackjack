package blackjack.domain.result;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import blackjack.domain.user.Player;

public class Profit {

	private final Map<Player, Money> playersProfit;

	public Profit(List<Player> players, Dealer dealer) {
		this.playersProfit = new HashMap<>();
		players.forEach(player -> putProfit(dealer, player));
	}

	private void putProfit(Dealer dealer, Player player) {
		playersProfit.put(player, calculateProfit(dealer, player));
	}

	private Money calculateProfit(Dealer dealer, Player player) {
		final double profitRate = player.state().profitRate(dealer);
		return player.calculateProfit(profitRate);
	}

	public Map<Player, Money> playerProfit() {
		return Collections.unmodifiableMap(playersProfit);
	}

	public double dealerProfit() {
		double dealerProfit = 0;
		for (Player player : playersProfit.keySet()) {
			dealerProfit -= playersProfit.get(player).getMoney();
		}
		return dealerProfit;
	}
}
