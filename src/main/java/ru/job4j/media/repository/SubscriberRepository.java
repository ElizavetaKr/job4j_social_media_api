package ru.job4j.media.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.media.model.Subscriber;

@Repository
public interface SubscriberRepository  extends ListCrudRepository<Subscriber, Integer> {
}
