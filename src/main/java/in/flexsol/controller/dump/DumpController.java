package in.flexsol.controller.dump;


import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import in.flexsol.delegate.dump.DumpService;
import in.flexsol.modal.dump.Dump;

@RestController
public class DumpController {

		@Qualifier("dumpServiceImpl")
		@Autowired
		DumpService dumpService;
	
	    @RequestMapping(value="/dump",method=org.springframework.web.bind.annotation.RequestMethod.POST,consumes= {"application/json"})
	    public ResponseEntity<Integer> saveDump(HttpEntity<JSONObject> httpEntity,HttpServletRequest request) {
				  
	    		try {
	    			 Dump dump = new Dump();
	    			 JSONObject dumpData = httpEntity.getBody();
				  	 dump.setDump(dumpData);
			         dump.setOrigin(request.getRemoteAddr());
			         int returnStatus = dumpService.saveDump(dump);
			         return new ResponseEntity<Integer>(returnStatus, HttpStatus.OK);
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    			return new ResponseEntity<Integer>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
	    		}
	    }
	    
	    
	    @RequestMapping(value="/dump",method=org.springframework.web.bind.annotation.RequestMethod.GET,produces= {"application/json"})
	    public ResponseEntity<List<Dump>> getDump(@RequestParam(required=false,defaultValue = "0") int limit,@RequestParam(required=false,defaultValue = "0") int offset,@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date createdgte) {
	    	List<Dump> dumpList = null;
	    	HttpStatus status =  HttpStatus.OK;
	    	try {
				  	 if(limit == 0 && offset == 0 && createdgte == null) {
				  		dumpList = dumpService.fetchAll();
				  	 } else {
				  		dumpList = dumpService.fetchAll(limit,offset,createdgte);
				  	 }
		  	} catch(Exception e) {
	    			e.printStackTrace();
	    			status =  HttpStatus.INTERNAL_SERVER_ERROR;
	    	}
	    	return new ResponseEntity<List<Dump>>(dumpList,status);
		}
}