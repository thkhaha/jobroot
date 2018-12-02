package movies.spring.data.neo4j.controller;

import java.util.Map;

import movies.spring.data.neo4j.services.PositionService;
import movies.spring.data.neo4j.util.Util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex Yoon
 * @author Tae Kim
 */
@RestController
@RequestMapping("/")
public class PositionController {

	private PositionService positionService;
	
	public PositionController(PositionService positionService) {
		if(positionService != null) {
			this.positionService = positionService;
		}
		this.positionService = new PositionService();
	}

    @GetMapping("/findPath")
	public Map<String, Object> findPath(@RequestParam(value = "startPosition",required = true) String startPosition, 
			@RequestParam(value = "endPosition",required = true) String endPosition, 
			@RequestParam(value = "limit",required = false) Integer limit) {
		return positionService.findPath(startPosition, endPosition, limit);
	}
}
