// package blackjack.domain.user;
//
// import static org.assertj.core.api.Assertions.*;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
//
// import blackjack.domain.card.Card;
// import blackjack.domain.card.Denomination;
// import blackjack.domain.card.Suit;
//
// public class DealerTest {
// 	@Test
// 	@DisplayName("딜러의 스코어가 더 높은지 확인하는 기능이 정상 작동하는지 확인")
// 	void dealer_score_higher_than_player_score() {
// 		//given
// 		Dealer dealer = new Dealer();
// 		Player player = new Player("pobi", 1000);
// 		//when
// 		dealer.addCard(Card.of(Denomination.NINE, Suit.CLOVER));
// 		player.addCard(Card.of(Denomination.FIVE, Suit.CLOVER));
// 		//then
// 		assertThat(dealer.compare(player.state.getCards())).isGreaterThan(0);
// 	}
//
// 	@Test
// 	@DisplayName("딜러의 스코어가 더 낮은지 확인하는 기능이 정상 작동하는지 확인")
// 	void dealer_score_lower_than_player_score() {
// 		//given
// 		Dealer dealer = new Dealer();
// 		Player player = new Player("pobi", 1000);
// 		//when
// 		dealer.addCard(Card.of(Denomination.NINE, Suit.CLOVER));
// 		player.addCard(Card.of(Denomination.TEN, Suit.CLOVER));
// 		//then
// 		assertThat(dealer.compare(player.state.getCards())).isLessThan(0);
// 	}
//
// 	@Test
// 	@DisplayName("딜러와 플레이어의 스코어가 같은지 확인하는 기능이 정상 작동하는지 확인")
// 	void dealer_score_equal_to_player_score() {
// 		//given
// 		Dealer dealer = new Dealer();
// 		Player player = new Player("pobi", 1000);
// 		//when
// 		dealer.addCard(Card.of(Denomination.NINE, Suit.CLOVER));
// 		player.addCard(Card.of(Denomination.NINE, Suit.HEART));
// 		//then
// 		assertThat(dealer.compare(player.state.getCards())).isEqualTo(0);
// 	}
// }
