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


    @ILog(level = Levels.SEVERE , keys = {"HttpRes","Done"})
    @GetMapping("/hello-test")
    public String res() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Holly", List.of("name", "total", "money", "done"));
        // ? use LoggingContext.put() to add data to the log
        LoggingContext.put("HttpRes", "Error 404");
        // some additional operations
        LoggingContext.put("end", "response");
        return "hello";
    }
    @ILog
    @GetMapping("/hello-test-2")
    public String res2() {
        Map<String, String> map = new HashMap<>();
        map.put("Name", "Mouzafir");
        map.put("Hobby", "exploring");
        map.put("Age", "22");
        map.put("Done", "true");
        // ? use LoggingContext.put() to add data to the log
        LoggingContext.put("HttpRes-2", map.toString());
        return "hello-2";
    }
    
    
}
