package in.flexsol.delegate.dump;

import java.util.Date;
import java.util.List;

import in.flexsol.modal.dump.Dump;
import in.flexsol.modal.site.Site;

public interface DumpService {

			public int saveDump(Dump dump);

			public List<Dump> fetchAll(int limit, int offset, Date createdgte);

			public List<Dump> fetchAll();	
			
			public List<Site> fetchSiteData();

			public Site getBaseSiteData(int randomSiteId);

			public Dump getPreviousSnapshot(String currentSiteId);

}
