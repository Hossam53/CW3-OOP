import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CardTest {
    public static void main(String[] args) {
        // create some cards
        Card card1 = new Card(1, "Card 1", Rank.UNIQUE);
        Card card2 = new Card(2, "Card 2", Rank.RARE);
        Card card3 = new Card(3, "Card 3", Rank.COMMON);
        Card card4 = new Card(4, "Card 4", Rank.UNCOMMON);
        Card card5 = new Card(5, "Card 5", Rank.UNIQUE);

        // test equals and hashCode
        assert card1.equals(new Card(1, "Card 1", Rank.UNIQUE));
        assert !card1.equals(card2);
        assert !card1.equals(new Card(1, "Card 1", Rank.RARE));
        assert card1.hashCode() == new Card(1, "Card 1", Rank.UNIQUE).hashCode();
        assert card1.hashCode() != card2.hashCode();

        // test compareTo
        assert card1.compareTo(card1) == 0;
        assert card1.compareTo(card2) < 0;
        assert card2.compareTo(card1) > 0;
        assert card1.compareTo(card3) < 0;
        assert card3.compareTo(card1) > 0;
        assert card1.compareTo(card4) < 0;
        assert card4.compareTo(card1) > 0;
        assert card1.compareTo(card5) == 0;

        // test HashSet behavior
        Set<Card> cardSet = new HashSet<>();
        cardSet.add(card1);
        cardSet.add(new Card(1, "Card 1", Rank.UNIQUE));
        cardSet.add(new Card(1, "Card 1", Rank.UNIQUE).withPrice(10));
        cardSet.add(new Card(1, "Card 1", Rank.UNIQUE).withPrice(20));
        assert cardSet.size() == 1;

        // test TreeSet ordering
        Set<Card> cardTreeSet = new TreeSet<>();
        cardTreeSet.add(card5);
        cardTreeSet.add(card3);
        cardTreeSet.add(card1);
        cardTreeSet.add(card2);
        cardTreeSet.add(card4);
        for (Card card : cardTreeSet) {
            System.out.println(card);
        }
        // Output should be:
        // Card{id=1, name='Card 1', rank=UNIQUE, price=0}
        // Card{id=5, name='Card 5', rank=UNIQUE, price=0}
        // Card{id=4, name='Card 4', rank=UNCOMMON, price=0}
        // Card{id=2, name='Card 2', rank=RARE, price=0}
        // Card{id=3, name='Card 3', rank=COMMON, price=0}
    }
}
