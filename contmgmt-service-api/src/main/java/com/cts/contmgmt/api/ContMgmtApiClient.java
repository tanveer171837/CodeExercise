package com.cts.contmgmt.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cts.contmgmt.api.request.DocumentRequest;
import com.cts.contmgmt.api.request.PostCommentRequest;
import com.cts.contmgmt.api.response.DownloadAllDocumentResponse;
import com.cts.contmgmt.api.response.DownloadDocumentResponse;

@FeignClient(name = "contmgmtApiClient", url = "${contmgmt.api.url:#{null}}")
public interface ContMgmtApiClient {

	@PostMapping("/upload")
	public void uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
			@RequestParam("docName") String docName, @RequestParam("docId") String docId,
			@RequestParam("author") String author);

	@GetMapping("/documents")
	public ResponseEntity<DownloadAllDocumentResponse> downloadAllDocuments();

	@PostMapping("/document")
	public ResponseEntity<DownloadDocumentResponse> downloadDocument(@RequestBody DocumentRequest request);

	@PostMapping("/delete")
	public void deleteDocument(@RequestBody DocumentRequest request);

	@PostMapping("/comment")
	public void postComment(@RequestBody PostCommentRequest request);

}
