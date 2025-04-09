package ru.job4j.media.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.media.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends ListCrudRepository<Post, Integer> {
    @Modifying(clearAutomatically = true)
    @Query("""
            update Post post set post.name = :name, post.description = :description
            where post.id = :id
            """)
    boolean updateNameAndDesc(@Param("name") String name, @Param("description") String description, @Param("id") int postId);

    @Modifying(clearAutomatically = true)
    @Query("""
            update Post post set post.file = null
            where post.id = ?1
            """)
    boolean deleteFileInPost(int postId);

    @Modifying(clearAutomatically = true)
    @Query("""
                delete from Post where id = ?1
            """)
    boolean delete(int postId);

    @Query("""
            select p from Post p where p.user.id in (
            select s.id from Subscriber s where s.requestUser.id = :userId)
            """)
    List<Post> findPostsSubscribers(@Param("userId") int userId);
}
