package in.flexsol.controller.greeting;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.flexsol.modal.greeting.Greeting;
import in.flexsol.service.greeting.GreetingService;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    
    @Qualifier("greetingServiceImpl")
    @Autowired
    GreetingService greetingService;
    
    
    @RequestMapping("/greetings")
    public List<Greeting> findGreetings() {
        return greetingService.fetchAll();
    }
    
    @RequestMapping(value="/greeting/{id}",method=org.springframework.web.bind.annotation.RequestMethod.GET)
    public Greeting findGreetings(@PathVariable("id") int id) {
        return greetingService.fetchById(id);
    }
    
    @RequestMapping(value="/greeting/save",method=org.springframework.web.bind.annotation.RequestMethod.POST)
    public ResponseEntity<Integer> saveGreeting(@RequestBody Greeting greeting) {
         int returnStatus = greetingService.save(greeting);
         return new ResponseEntity<Integer>(returnStatus, HttpStatus.OK);
    }
    
    @RequestMapping(value="/greeting/update",method=org.springframework.web.bind.annotation.RequestMethod.PUT)
    public int updateGreeting(@RequestBody Greeting greeting) {
        return greetingService.update(greeting);
    }
    
    @RequestMapping(value="/greeting/delete",method=org.springframework.web.bind.annotation.RequestMethod.DELETE)
    public int deleteGreeting(@RequestBody Greeting greeting) {
         return greetingService.delete(greeting);
    }
    
}
