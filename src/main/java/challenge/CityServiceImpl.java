package challenge;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CityResource resource;

	private static final String YES = "yes";
	private static final String NO = "no";
	Set<String> visited = new HashSet<>();

	/**
	 * Service method to check if two cities are connected connected.  
	 * The actually determination is delegated to a recursive method.
	 *
	 * @param orig the originating city
	 * @param dest the destination city
	 * @return the string
	 */
	public String isConnected(String orig, String dest) {
		visited.clear();
		
		// Determine if two cities are connected
		if (connectionFound(orig, dest))
			return YES;
		else
			return NO;

	}

	/**
	 * Helper method to Determines if two cities are connected by recursion.   The method navigates through the 
	 * grid of city connections to determine if there is a path between the two cities.  The grid is pre-assembled
	 * in another service class which is made accessible to this class through dependency injection. 
	 *
	 * @param orig the orig
	 * @param dest the dest
	 * @return true, if successful
	 */
	private boolean connectionFound(String orig, String dest) {
		Boolean found = false;
		
		// If the cities are not valid ignore
		if (!getResource().getCityGrid().containsKey(orig) || !getResource().getCityGrid().containsKey(dest)) {
			return false;
		}

		
		for (String connectedCity : getResource().getCityGrid().get(orig)) {
			logger.info("{} - {}", orig, connectedCity);

			if (visited.contains(orig + "-" + connectedCity) || visited.contains(connectedCity + "-" + orig)) {
				continue;
			}
			
			if (connectedCity.equals(dest)) {
				found = true;
				return found;
			}else {
				visited.add(orig + "-" + connectedCity);
				visited.add(connectedCity + "-" + orig);

				found = connectionFound(connectedCity, dest);				
			}
		}

		return found;
	}

	public CityResource getResource() {
		return resource;
	}

	public void setResource(CityResource resource) {
		this.resource = resource;
	}

}
