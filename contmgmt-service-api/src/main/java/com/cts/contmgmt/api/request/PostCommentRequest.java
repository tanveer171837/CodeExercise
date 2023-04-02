package com.cts.contmgmt.api.request;

import lombok.Data;

@Data
public class PostCommentRequest {
	
	private Integer docId;

	private String commentBy;

	private String comments;

}
