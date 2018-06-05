package in.flexsol.dao.greeting;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import in.flexsol.modal.greeting.Greeting;

@Repository("greetingDaoImpl")
public class GreetingDaoImpl  implements GreetingDao  {

	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public List<Greeting> fetchAll() {
		String sql = "SELECT id,content FROM GREETING";
		List<Greeting> greetings = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Greeting>(Greeting.class));
		return greetings;	
	}

	@Override
	public Greeting fetchById(int id) {
		String sql = "select * from GREETING where id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {id} , new BeanPropertyRowMapper<Greeting>(Greeting.class));
	}

	@Override
	public int save(Greeting greeting) {
		String sql = "insert into GREETING (content) values (?)";
		return jdbcTemplate.update(sql,new Object[] {greeting.getContent()});
	}

	@Override
	public int update(Greeting greeting) {
		String sql = "update GREETING set content = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] {greeting.getContent(),greeting.getId()});
	}

	@Override
	public int delete(Greeting greeting) {
		String sql = "delete from GREETING where id = ?";
		return jdbcTemplate.update(sql,new Object[] {greeting.getId()});
		
	}

	
}
