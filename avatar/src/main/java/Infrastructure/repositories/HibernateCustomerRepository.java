package Infrastructure.repositories;

import Infrastructure.repositories.entities.CustomerProfilePhotos;
import domain.models.Customer;
import domain.repositories.CustomerQuery;
import domain.repositories.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class HibernateCustomerRepository implements CustomerRepository {
    private final EntityManager entityManager;

    public HibernateCustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> find(CustomerQuery query) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(CustomerProfilePhotos.class);
        var root = cq.from(CustomerProfilePhotos.class);
        cq.select(root).where(conditions(query,cb,root));

        return entityManager.createQuery(cq)
                .getResultStream()
                .map(CustomerProfilePhotos::toDomain)
                .collect(Collectors.groupingBy(Customer::id))
                .entrySet()
                .stream()
                .map(entry -> new Customer(entry.getKey(),
                        entry.getValue()
                                .stream()
                                .flatMap(customer -> customer
                                        .profilePhoto().stream()).toList()))
                .toList();
    }

    private Predicate[] conditions(CustomerQuery query, CriteriaBuilder cb, Root<CustomerProfilePhotos> root) {
        return Stream.of(query.getIds().map(id -> cb.in(root.get("compositeKey").get("customerId")).value(id)))
                .flatMap(Optional::stream)
                .toArray(Predicate[]::new);
    }
}
