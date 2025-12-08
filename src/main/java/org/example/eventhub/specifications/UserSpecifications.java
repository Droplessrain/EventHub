package org.example.eventhub.specifications;

import jakarta.persistence.criteria.Predicate;
import org.example.eventhub.filterEntity.SearchUsersFilter;
import org.example.eventhub.model.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecifications {
    public static Specification<User> withFilters(SearchUsersFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.username() != null) {
                predicates.add(cb.equal(root.get("username"), filter.username()));
            }

            if (filter.role() != null) {
                predicates.add(cb.equal(root.get("role"), filter.role()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
