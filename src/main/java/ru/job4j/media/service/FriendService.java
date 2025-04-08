package ru.job4j.media.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.media.model.Friend;
import ru.job4j.media.model.Subscriber;
import ru.job4j.media.repository.friend.FriendRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class FriendService {
    private final FriendRepository friendRepository;
    private final SubscriberService subscriberService;

    public Friend saveNewFriend(Friend friend, Subscriber subscriber) {
        subscriberService.save(subscriber);
        return friendRepository.save(friend);
    }

    public void delete(int requestUserId, int targetUserId) {
        friendRepository.deleteByRequestIdAndTargetId(requestUserId, targetUserId);
        friendRepository.deleteByRequestIdAndTargetId(targetUserId, requestUserId);
        subscriberService.deleteByRequestIdAndTargetId(requestUserId, targetUserId);
    }

    public Optional<Friend> findByRequestIdAndTargetId(int requestUserId, int targetUserId) {
        return friendRepository.findByRequestIdAndTargetId(requestUserId, targetUserId);
    }
}
