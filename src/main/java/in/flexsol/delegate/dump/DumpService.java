package in.flexsol.delegate.dump;

import java.util.Date;
import java.util.List;

import in.flexsol.modal.dump.Dump;

public interface DumpService {

			public int saveDump(Dump dump);

			public List<Dump> fetchAll(int limit, int offset, Date createdgte);

			public List<Dump> fetchAll();	
}
