package fun;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

public class GameTests {
	
	public static int counter = 1;
	public static Map<Integer, Integer> map = new HashMap<>();
	public static int id = 0;

	public int fib(int counter) {
		if (counter == 1 || counter == 2) {
			return 1;
		}
		if (map.containsKey(counter)) {
			return map.get(counter);
		}
		int value = fib(counter - 1) + fib(counter - 2);
		map.put(counter, value);
		return value;
	}

	public JSONObject getJSONResonse(String nameParameter) throws Exception {
		String url = "";
		if (nameParameter == null) {
			url = "http://localhost:8080/game";
		} else {
			url = "http://localhost:8080/game?name=" + nameParameter;
		}
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setReadTimeout(2000);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		id = fib(counter++);
		JSONObject myResponse = new JSONObject(response.toString());
		return myResponse;
	}
	
	@Test
	public void test_without_name_param() throws Exception {
		JSONObject myResponse = getJSONResonse(null);
		assertEquals("The out put is not 'Playing Sudoku is fun!' but " + myResponse.getString("text"),
				"Playing Sudoku is fun!", myResponse.getString("text"));
	}
	
	@Test
	public void test_reload_id() throws Exception {
		for (int i = 0; i < 7; i++) {
			JSONObject myResponse = getJSONResonse(null);
			assertEquals("The id is not " + id + " but " + myResponse.getInt("id"), id, myResponse.getInt("id"));
		}
	}
	
	@Test
	public void test_with_name_param() throws Exception {
		JSONObject myResponse = getJSONResonse("game");
		assertEquals("The out put is not 'Playing game is fun!' but " + myResponse.getString("text"),
				"Playing game is fun!", myResponse.getString("text"));
	}
}
