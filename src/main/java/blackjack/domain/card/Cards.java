package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    public static final int BLACK_JACK = 21;
    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Cards of(List<Card> values) {
        return new Cards(values);
    }

    public int calculateScore() {
        int result = 0;
        result += calculateScoreWithOutAce();
        result += calculateAceScore(result);
        return result;
    }

    private int calculateScoreWithOutAce() {
        return cards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(card -> card.calculateScore())
            .sum();
    }

    private int calculateAceScore(int result) {
        List<Card> aceCards = cards.stream()
            .filter(Card::isAce)
            .collect(Collectors.toList());
        int sum = 0;
        for (Card card : aceCards) {
            int aceScore = card.getAceScore(result);
            result += aceScore;
            sum += aceScore;
        }
        return sum;
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACK_JACK;
    }

    public boolean isBust() {
        return calculateScore() > BLACK_JACK;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
