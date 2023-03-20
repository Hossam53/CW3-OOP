import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class HollomonClient {

    private Socket socket;
    private  InputStream is;
    private OutputStream os;

    public HollomonClient(String server, int port) throws IOException {
        socket = new Socket(server, port);
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    public List<Card> login(String username, String password) throws IOException {
        // Send the username and password to the server
        os.write((username.toLowerCase() + "\n" + password + "\n").getBytes());
        os.flush();

        // Read the response from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String response = reader.readLine();

        if (response != null && response.startsWith("User " + username.toLowerCase() + " logged in successfully.")) {
            // Login successful, read card data
            List<Card> cards = new ArrayList<>();
            CardInputStream cis = new CardInputStream(is);
            Card card;
            while ((card = cis.readCard()) != null) {
                cards.add(card);
            }
            // Sort the cards
            Collections.sort(cards);

            return cards;
        } else {
            // Login unsuccessful, return null
            return null;
        }
    }


    public void close() throws IOException {
        is.close();
        os.close();
        socket.close();
    }
    public long getCredits() throws IOException {
        // Send the CREDITS command to the server
        os.write("CREDITS\n".getBytes());
        os.flush();

        // Read the response from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String response = reader.readLine();

        if (response != null && response.startsWith("CREDITS ")) {
            // Extract the credit amount from the response
            try {
                return Long.parseLong(response.substring(8));
            } catch (NumberFormatException e) {
                // Invalid response format
                throw new IOException("Invalid response from server");
            }
        } else {
            // Invalid response
            throw new IOException("Invalid response from server");
        }
    }


}
