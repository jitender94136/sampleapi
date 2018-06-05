package in.flexsol.dao.greeting;

import java.util.List;

import in.flexsol.modal.greeting.Greeting;

public interface GreetingDao {
	
	
	public List<Greeting> fetchAll();
	
	public Greeting fetchById(int id);

	public int save(Greeting greeting);
	
	public int update(Greeting greeting);
	
	public int delete(Greeting greeting);	
}
