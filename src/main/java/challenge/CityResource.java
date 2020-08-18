package challenge;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * This is the reference Bean class which services as a wrapper for a reference data to be used through out the app.  
 * One such refernce data is the grid. The city grid maps out all the connected cities.
 * 
 */
@Component
public class CityResource implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	Map<String, List<String>> cityGrid;

	/**
	 * Constructs the city grid
	 *
	 * @throws Exception the exception
	 */
	@PostConstruct
	public void init() throws Exception {
		List<String> connectedCities = new ArrayList<>();

		Resource resource = resourceLoader.getResource("classpath:city.txt");

		InputStream in = resource.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;
			connectedCities.add(line.trim());
		}

		setCityGrid(createGrid(connectedCities));

		reader.close();
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Map<String, List<String>> getCityGrid() {
		return cityGrid;
	}

	public void setCityGrid(Map<String, List<String>> cityGrid) {
		this.cityGrid = cityGrid;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	/**
	 * Creates the grid by reading each line and creating the respecting map for easier searching.
	 *
	 * @param roads the roads
	 * @return the map
	 */
	public Map<String, List<String>> createGrid(List<String> roads) {
		Map<String, List<String>> grid = new HashMap<>();

		roads.stream().forEach(road -> {
			String orig = road.split(",")[0].trim();
			String dest = road.split(",")[1].trim();

			if (!grid.containsKey(orig)) {
				grid.put(orig, Stream.of(dest).collect(Collectors.toCollection(ArrayList::new)));
			} else {
				grid.get(orig).add(dest);
			}

			if (!grid.containsKey(dest)) {
				grid.put(dest, Stream.of(orig).collect(Collectors.toCollection(ArrayList::new)));
			} else {
				grid.get(dest).add(orig);
			}

		});
		return grid;
	}

	/**
	 * Prints the grid.  This is a helper method used for debugging purpose only.
	 *
	 * @param grid the grid
	 * @return the string
	 */
	private String printGrid(Map<String, List<String>> grid) {
		StringBuilder dests = new StringBuilder();
		for (Entry<String, List<String>> orig : grid.entrySet()) {
			dests.append(orig.getKey() + "-->");
			for (String dest : grid.get(orig.getKey())) {
				dests.append(dest + ", ");
			}
			dests.append("\n");
		}

		return dests.toString();

	}
}
