package in.flexsol.service.greeting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.flexsol.dao.greeting.GreetingDao;
import in.flexsol.modal.greeting.Greeting;




@Service("greetingServiceImpl")
public class GreetingServiceImpl implements GreetingService {

	@Qualifier("greetingDaoImpl")
	@Autowired
	GreetingDao greetingDao;
	
	@Override
	public List<Greeting> fetchAll() {
		return greetingDao.fetchAll();
		
	}

	@Override
	public Greeting fetchById(int id) {		
		return greetingDao.fetchById(id);
	}

	@Override
	public int save(Greeting greeting) {
		return greetingDao.save(greeting);
	}

	@Override
	public int update(Greeting greeting) {
		return greetingDao.update(greeting);
	}

	@Override
	public int delete(Greeting greeting) {
		return greetingDao.delete(greeting);	
	}

}
