package ru.job4j.media.repository.friend;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.media.model.Friend;

import java.util.Optional;

@Repository
public interface FriendRepository extends ListCrudRepository<Friend, Integer> {
    @Query("""
            select f from Friend f
            where f.requestUser.id = :requestUserId AND f.targetUser.id = :targetUserId
            """)
    Optional<Friend> findByRequestIdAndTargetId(@Param("requestUserId") int requestUserId, @Param("targetUserId") int targetUserId);

    @Modifying(clearAutomatically = true)
    @Query("""
                delete from Friend f
            where f.requestUser.id = :requestUserId AND f.targetUser.id = :targetUserId
            """)
    void deleteByRequestIdAndTargetId(@Param("requestUserId") int requestUserId, @Param("targetUserId") int targetUserId);
}
