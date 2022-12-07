package telran.java45.post.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java45.post.dto.DatePeriodDto;
import telran.java45.post.dto.NewCommentDto;
import telran.java45.post.dto.NewPostDto;
import telran.java45.post.dto.PostDto;
import telran.java45.post.service.PostService;

@RestController
@RequestMapping("/forum")
@RequiredArgsConstructor
public class PostController {

	final PostService service;


	@PostMapping("/post/{author}")
	public PostDto addPost(@RequestBody NewPostDto newPost, @PathVariable String author) {
		return service.addNewPost(newPost, author);
	}

	@GetMapping("/post/{id}")
	public PostDto getPost(@PathVariable String id) {
		return service.getPost(id);
	}

	@DeleteMapping("/post/{id}")
	public PostDto removePost(@PathVariable String id) {
		return service.removePost(id);
	}

	@PutMapping("/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto postUpdateDto) {
		return service.updatePost(postUpdateDto, id);
	}

	@PutMapping("/post/{id}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addLike(@PathVariable String id) {
		service.addLike(id);
	}

	@PutMapping("/post/{id}/comment/{author}")
	public PostDto addComment(@PathVariable String id, @PathVariable String author,
			@RequestBody NewCommentDto newCommentDto) {
		return service.addComment(id, author, newCommentDto);
	}

	@GetMapping("/posts/author/{author}")
	public Iterable<PostDto> getPostsByAuthor(@PathVariable("author") String name) {
		return service.findPostsByAuthor(name);
	}
	
	@PostMapping("/posts/tags")
	public Iterable<PostDto> findPostsByTags(@RequestBody List<String> tags) {
		return service.findPostsByTags(tags);
	}
	
	@PostMapping("/posts/period")
	public Iterable<PostDto> findPostsByDate(@RequestBody DatePeriodDto datePeriodDto) {
		return service.findPostsByDates(datePeriodDto);
	}

}
