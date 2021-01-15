package vn.betting.automation.infrastructure.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperHolder implements ApplicationContextAware {
    private static ObjectMapperHolder instance = new ObjectMapperHolder();
    private ObjectMapper mapper;
    
    /**
     * Get ObjectMapperHolder which contains ObjectMapper inside.
     * @return
     */
    public static final ObjectMapperHolder instance() {
        return instance;
    }
    
    /**
     * @return the mapper
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ObjectMapper appMapper = applicationContext.getBean(ObjectMapper.class);
        if (appMapper == null) {
            throw new IllegalArgumentException("ObjectMapper must be defined in application context");
        }
        instance().mapper = appMapper;
    }
}
