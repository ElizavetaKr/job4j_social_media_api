package ru.job4j.media.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.media.model.Subscriber;
import ru.job4j.media.repository.subscriber.SubscriberRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;

    public Subscriber save(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    public Optional<Subscriber> findByRequestIdAndTargetId(int requestUserId, int targetUserId) {
        return subscriberRepository.findByRequestIdAndTargetId(requestUserId, targetUserId);
    }

    public void deleteByRequestIdAndTargetId(int requestUserId, int targetUserId) {
        subscriberRepository.deleteByRequestIdAndTargetId(requestUserId, targetUserId);
    }
}
