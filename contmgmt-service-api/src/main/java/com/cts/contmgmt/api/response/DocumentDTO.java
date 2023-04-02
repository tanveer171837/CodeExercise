package com.cts.contmgmt.api.response;

import com.cts.contmgmt.api.placeholder.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
	
	private long docId;
	
	private String docName;
	
	private String docType;
	
	private byte[] docContent;
	
	private Post post;

}
