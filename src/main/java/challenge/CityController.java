package challenge;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class CityController.
 * 
 * This class is the city controller which exposes all city service end points 
 */
@RestController
public class CityController {

	/** The service. */
	@Autowired
	CityService service;
	
	/**
	 * Checks if two origin and destination cities are connected.
	 *
	 * @param origin the origin
	 * @param destination the destination
	 * @return the string
	 */
	@GetMapping(path="/connected", produces=MediaType.APPLICATION_JSON_VALUE)
	public String isConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
		return service.isConnected(origin, destination);
	}
	
	
	
}
