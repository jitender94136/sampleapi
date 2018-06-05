package in.flexsol;



import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;


@SpringBootApplication
public class Application {



	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
//    @Bean
//    public TomcatEmbeddedServletContainerFactory tomcatFactory() {  // if datasouce defind in tomcat xml configuration then no need to create this bean
//        return new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(
//                    Tomcat tomcat) {
//                tomcat.enableNaming();
//                return super.getTomcatEmbeddedServletContainer(tomcat);
//            }
//            @Override                   // create JNDI resource
//            protected void postProcessContext(Context context) {
//                ContextResource resource = new ContextResource();
//                resource.setName("jdbc/jndiDataSource");
//                resource.setType(DataSource.class.getName());
//                resource.setProperty("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
//                resource.setProperty("url", env.getProperty("spring.datasource.url"));
//                resource.setProperty("username",env.getProperty("spring.datasource.username"));
//                resource.setProperty("password", env.getProperty("spring.datasource.password"));
//                context.getNamingResources().addResource(resource);
//            }
//        };
//    }
//    
//    
//  
//    
//    @Bean
//    public DataSource jndiDataSource() throws IllegalArgumentException,
//                                              NamingException {
//        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();           // create JNDI data source
//        bean.setJndiName("java:comp/env/jdbc/jndiDataSource");  // jndiDataSource is name of JNDI data source 
//        bean.setProxyInterface(DataSource.class);
//        bean.setLookupOnStartup(false);
//        bean.afterPropertiesSet();
//        return (DataSource) bean.getObject();
//    }
    
    
    
    
}
