package com.github.east196.starter;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.east196.easyboot.core.DynamicSpecifications;
import com.github.east196.easyboot.core.PageResponse;
import com.github.east196.easyboot.core.SearchTerm;
import com.github.east196.easyboot.json.Json;

@RestController
public class PersonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Person.class);

	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(value = "/persons/show", method = RequestMethod.POST)
	public PageResponse<List<Person>> page(
			@RequestParam(value = "sort", required = false, defaultValue = "id,desc") String sort,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
			@RequestBody Map<String, SearchTerm> search) {
		LOGGER.info("sort:{},page:{},size:{},search:{}", sort, page, size, Json.encode(search));
		String[] propertyAndDirection = StringUtils.split(sort, ",");
		String property = propertyAndDirection[0];
		String direction = propertyAndDirection[1];
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), property));
		Specification<Person> spec = DynamicSpecifications.bySearch(search, Person.class);
		Page<Person> pagedPersons = personRepository.findAll(spec, pageRequest);
		LOGGER.info("pagedPersons:{}", Json.encode(pagedPersons));
		List<Person> data = pagedPersons.getContent();
		PageMetadata pageMetadata = new PageMetadata(pagedPersons.getSize(), pagedPersons.getNumber(),
				pagedPersons.getTotalElements(), pagedPersons.getTotalPages());
		PageResponse<List<Person>> pageResponse = new PageResponse<List<Person>>("200", "ok", data, pageMetadata);
		return pageResponse;
	}
}
