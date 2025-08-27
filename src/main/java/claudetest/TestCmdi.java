package claudetest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestCmdi {
    @RequestMapping("cmdi")
    public void cmdi(String cmd) throws IOException {
        Runtime.getRuntime().exec(cmd);
    }
}
