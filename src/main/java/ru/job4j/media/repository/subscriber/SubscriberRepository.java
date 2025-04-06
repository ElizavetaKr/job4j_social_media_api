package ru.job4j.media.repository.subscriber;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.media.model.Subscriber;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends ListCrudRepository<Subscriber, Integer> {

    @Query("""
            select s from Subscriber s
            where s.requestUser.id = :requestUserId AND s.targetUser.id = :targetUserId
            """)
    Optional<Subscriber> findByRequestIdAndTargetId(@Param("requestUserId") int requestUserId, @Param("targetUserId") int targetUserId);

    @Modifying(clearAutomatically = true)
    @Query("""
                delete from Subscriber s
            where s.requestUser.id = :requestUserId AND s.targetUser.id = :targetUserId
            """)
    void deleteByRequestIdAndTargetId(@Param("requestUserId") int requestUserId, @Param("targetUserId") int targetUserId);
}
