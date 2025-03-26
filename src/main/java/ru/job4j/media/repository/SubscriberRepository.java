package ru.job4j.media.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.job4j.media.model.Subscriber;

public interface SubscriberRepository  extends ListCrudRepository<Subscriber, Integer> {
}
