package com.github.east196.easyboot.core;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.github.east196.easyboot.Easy;
import com.github.east196.easyboot.core.SearchTerm.Relation;
import com.github.east196.starter.Person;
import com.google.common.collect.Lists;

@SuppressWarnings("all")
public class DynamicSpecifications {

	public static <T> Specification<T> bySearch(Map<String, SearchTerm> search, Class<T> entityClass) {
		final Map<String, Type> fieldTypes = new HashMap<>();
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			fieldTypes.put(field.getName(), field.getGenericType());
		}
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

				List<Predicate> predicates = Lists.newArrayList();
				for (Entry<String, SearchTerm> entry : search.entrySet()) {
					String fieldName = entry.getKey();
					Path expression = root.get(fieldName);
					SearchTerm searchTerm = entry.getValue();

					for (Entry<String, String> term : searchTerm.entrySet()) {
						System.out.println(term);
						Relation relation = Relation.valueOf(term.getKey().toUpperCase());
						String value = term.getValue();
						if(StringUtils.isBlank(value)){
							continue;
						}
						
						Object object = value;
						Type type = fieldTypes.get(fieldName);
						if (null != type) {
							if (type.toString().equals("class java.util.Date")) {
								object = Easy.praseDate(value);
							} else if (type.toString().equals("class java.lang.Boolean")) {
								object = Boolean.parseBoolean(value);
							} else if (type.toString().equals("class java.lang.Integer") && !"null".equals(value)) {
								object = Integer.parseInt(value);
							} else if (type.toString().equals("class java.lang.Double")) {
								object = Double.parseDouble(value);
							} else if (type.toString().equals("class java.lang.Long")) {
								object = Long.parseLong(value);
							} else {
								object = value;
							}
							System.out.println(expression+""+object);
						}
						
						switch (relation) {
						case LIKE:
							predicates.add(builder.like(expression, "%" + object + "%"));
							break;
						case EQ:
							predicates.add(builder.equal(expression, object));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) object));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) object));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) object));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) object));
							break;
						}
					}

				}

				// 将所有条件用 and 联合起来
				if (!predicates.isEmpty()) {
					return builder.and(predicates.toArray(new Predicate[predicates.size()]));
				}

				return builder.conjunction();
			}
		};
	}

}
