import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CardInputStream extends InputStream {

    private BufferedReader reader;

    public CardInputStream(InputStream input) {
        this.reader = new BufferedReader(new InputStreamReader(input));
    }

    @Override
    public int read() throws IOException {
        return reader.read();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public Card readCard() throws IOException {
        String tag = readResponse();
        if (!tag.equals("CARD")) {
            return null;
        }
        Long id = Long.valueOf(readResponse());
        String name = readResponse();
        Rank rank = Rank.valueOf(readResponse());
        int price = Integer.parseInt(readResponse());
        return new Card(id, name, rank, price);
    }

    public String readResponse() throws IOException {
        return reader.readLine();
    }

}

