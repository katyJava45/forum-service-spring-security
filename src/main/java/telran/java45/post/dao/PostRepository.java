package telran.java45.post.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import telran.java45.post.model.Post;

public interface PostRepository extends CrudRepository<Post, String> {
	Stream<Post> findByAuthorIgnoreCase(String author);

	Stream<Post> findByTagsInIgnoreCase(List<String> tags);

	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);

}
