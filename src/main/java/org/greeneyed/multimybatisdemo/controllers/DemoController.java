package org.greeneyed.multimybatisdemo.controllers;

import java.util.HashMap;
import java.util.Map;
import org.greeneyed.multimybatisdemo.mappers.one.OneMapper;
import org.greeneyed.multimybatisdemo.mappers.another.AnotherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@Autowired
	OneMapper oneMapper;

	@Autowired
	AnotherMapper anotherMapper;

	@RequestMapping("/test_mappers")
	public Map<String, String> testMappers() {
		Map<String, String> result = new HashMap<>();
		result.put("Mapper", "Database version");
		result.put("oneMapper", oneMapper.getVersion());
		result.put("anotherMapper", anotherMapper.getVersion());
		return result;
	}

}
