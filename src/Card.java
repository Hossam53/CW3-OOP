import java.util.Objects;

public class Card implements Comparable<Card> {
    private final long id;
    private final String name;
    private final Rank rank;
    private final long price;

    public Card(long id, String name, Rank rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = 0;
    }

    public Card(long id, String name, Rank rank, long price) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }

    public long getPrice() {
        return price;
    }

    public Card withPrice(long newPrice) {
        return new Card(id, name, rank, newPrice);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rank);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Card other = (Card) obj;
        return id == other.id && Objects.equals(name, other.name) && rank == other.rank;
    }

    @Override
    public int compareTo(Card other) {
        if (this == other) {
            return 0;
        }
        int rankCompare = rank.compareTo(other.rank);
        if (rankCompare != 0) {
            return rankCompare;
        }
        int nameCompare = name.compareTo(other.name);
        if (nameCompare != 0) {
            return nameCompare;
        }
        return Long.compare(id, other.id);
    }
}
