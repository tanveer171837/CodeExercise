package com.cts.contmgmt.api;

import java.util.List;

import com.cts.contmgmt.api.placeholder.Comment;
import com.cts.contmgmt.api.placeholder.Post;

public interface IPlaceholderService {
	
	void addPost(Post post);
	
	Post getPost(String docId, String author);
	
	void  postComment(Comment comment);
	
	List<Comment> getComment(String docId);

}
