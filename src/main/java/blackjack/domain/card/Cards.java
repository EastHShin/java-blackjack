package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.user.Score;

public class Cards {
	private static final int ELEVEN_ACE_SCORE = 11;

	private final List<Card> cards;
	private Score score;

	public Cards() {
		this.cards = new ArrayList<>();
		this.score = Score.initialScore();
	}

	public void addCard(final Card card) {
		cards.add(card);
		this.score = this.score.addBy(card.getScore());
		if (hasAce()) {
			this.score = Score.from(calculateOptimalScoreWithAce());
		}
	}

	private int calculateOptimalScoreWithAce() {
		final int theNumberOfAce = countAceCard();
		final int scoreWithoutAce = calculateNotAceCardScore();
		List<Integer> possible = getPossibleAceScores(theNumberOfAce);
		final int optimalScore = generateOptimalScore(scoreWithoutAce, possible);
		return optimalScore;
	}

	private int countAceCard() {
		return (int)cards.stream()
			.filter(Card::isAce)
			.count();
	}

	private int calculateNotAceCardScore() {
		return cards.stream()
			.filter(card -> !card.isAce())
			.mapToInt(Card::getScore)
			.sum();
	}

	private List<Integer> getPossibleAceScores(final int aceCnt) {
		List<Integer> possibleScores = new ArrayList<>();
		for (int i = 0; i <= aceCnt; i++) {
			possibleScores.add(i + ELEVEN_ACE_SCORE * (aceCnt - i));
		}
		return possibleScores;
	}

	private int generateOptimalScore(final int scoreWithoutAce, final List<Integer> possible) {
		int optimalScore = scoreWithoutAce;
		List<Integer> notBustedScores = possible.stream()
			.filter(score -> !Score.from(score + scoreWithoutAce).hasBustState())
			.collect(Collectors.toList());

		for (int possibleScore : notBustedScores) {
			optimalScore = Math.max(optimalScore, possibleScore + scoreWithoutAce);
		}
		return optimalScore;
	}

	private boolean hasAce() {
		return this.cards.stream().anyMatch(Card::isAce);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public int getScore() {
		return score.getScore();
	}

	public boolean isBust() {
		return score.hasBustState();
	}

	public int compare(final Cards otherCards) {
		return this.score.compareTo(otherCards.score);
	}

	public boolean isHit() {
		return this.score.isHit();
	}

	public boolean isBlackJack() {
		return cards.size() == 2 && this.score.hasBlackJackScore();
	}
}
