package telran.java45.post.service;

import java.util.List;

import telran.java45.post.dto.DatePeriodDto;
import telran.java45.post.dto.NewCommentDto;
import telran.java45.post.dto.NewPostDto;
import telran.java45.post.dto.PostDto;

public interface PostService {
	PostDto addNewPost(NewPostDto newPost, String author);

	PostDto getPost(String id);

	PostDto removePost(String id);

	PostDto updatePost(NewPostDto postUpdateDto, String id);

	void addLike(String id);

	PostDto addComment(String id, String author, NewCommentDto newCommentDto);

	Iterable<PostDto> findPostsByAuthor(String author);

	Iterable<PostDto> findPostsByTags(List<String> tags);

	Iterable<PostDto> findPostsByDates(DatePeriodDto datePeriodDto);
}
