package vn.betting.automation.infrastructure.handle;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class StringJacksonModule extends SimpleModule {

    private static final long serialVersionUID = 3858227721688766313L;
    
    public StringJacksonModule() {
        addDeserializer(String.class, new JacksonStringDeserializer());
    }

}
