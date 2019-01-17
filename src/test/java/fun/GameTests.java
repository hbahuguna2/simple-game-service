package fun;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameTests {

	private MockMvc mockMvc;
	
	@InjectMocks
	private GameController gameController;
	
	private int counter = 1;
    private Map<Integer,Integer> map = new HashMap<>();
    private int fib(int counter) {
		if(counter == 1 || counter == 2) {
			return 1;
		}
		if(map.containsKey(counter)) {
			return map.get(counter);
		}
		int value = fib(counter - 1) + fib(counter - 2);
		map.put(counter, value);
		return value;
    }
	
	@Before
	public void setUp() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
	}
	
	@Test
	public void test_without_name_param() throws Exception{
		mockMvc.perform(get("/game")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.text", Matchers.is("Playing Sudoku is fun!")));
	}
	
	@Test
	public void test_reload_id() throws Exception{
		for(int i=0;i<7;i++) {
			mockMvc.perform(get("/game")
					.accept(APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id", Matchers.is(fib(counter++))));
		}
	}
	
	@Test
	public void test_with_name_param() throws Exception{
		mockMvc.perform(get("/game?name=game")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.text", Matchers.is("Playing game is fun!")));
	}
}
