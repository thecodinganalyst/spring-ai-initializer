package com.thecodinganalyst.spring_ai_demo.samples.tools;

import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDate;

public class WonderlandDateTool {

    @Tool(
            name = "WonderlandDate",
            description = "Gets the current date in Wonderland"
    )
    public String getWonderlandDate(){
        return LocalDate.of(2027, 12, 25).toString();
    }
}
