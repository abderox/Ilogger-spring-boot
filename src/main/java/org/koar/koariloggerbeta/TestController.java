package org.koar.koariloggerbeta;

import org.koar.koariloggerbeta.ilogger.AOP.ILog;
import org.koar.koariloggerbeta.ilogger.AOP.Levels;
import org.koar.koariloggerbeta.ilogger.LoggingContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class TestController {


    @ILog(level = Levels.WTF , keys = {"HttpRes","Done"})
    @GetMapping("/hello-test")
    public String res() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Holly", List.of("name", "total", "money", "done"));
        // ? use LoggingContext.put() to add data to the log
        LoggingContext.put("HttpRes", map.toString());
        // some additional operations
        LoggingContext.put("Done", "response");
        return "hello";
    }
    @ILog
    @GetMapping("/hello-test-2")
    public String res2() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Holly", List.of("name", "total", "money", "done"));
        // ? use LoggingContext.put() to add data to the log
        LoggingContext.put("HttpRes-2", map.toString());
        return "hello-2";
    }
    
    
}
