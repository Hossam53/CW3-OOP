import java.io.IOException;
import java.util.List;

public class HollomonClientTest {
    public static void main(String[] args) throws IOException {
        HollomonClient client = new HollomonClient("netsrv.cim.rhul.ac.uk", 1812);
        List<Card> cards = client.login("development", "withtruelearn");
        System.out.println(cards);
        client.close();
    }
}
