package fun;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private static final String template = "Playing %s is fun!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/game")
    public Game greeting(@RequestParam(value="name", defaultValue="Sudoku") String name) {
        return new Game(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
