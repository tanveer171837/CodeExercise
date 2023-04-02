package com.cts.contmgmt.customsvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.contmgmt.api.request.DocumentRequest;
import com.cts.contmgmt.api.request.PostCommentRequest;
import com.cts.contmgmt.api.response.DownloadAllDocumentResponse;
import com.cts.contmgmt.api.response.DownloadDocumentResponse;
import com.cts.contmgmt.customsvc.service.ContMgmtService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/contmgmtApi")
public class ContMgmtController {
	
	@Autowired
	private ContMgmtService service;

	@ApiOperation(value = "Upload Document")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 400, message = "Invalid Request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/upload")
	public void uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
			@RequestParam("docName") String docName, @RequestParam("docId") String docId, @RequestParam("author") String author) {
		log.info("-------ContMgmtController::uploadDocument starts--------");
		service.uploadDocument(docName, file, title, docId, author);
		log.info("-------ContMgmtController::uploadDocument ends--------");
	}
	
	@GetMapping("/documents")
	public ResponseEntity<DownloadAllDocumentResponse> downloadAllDocuments() {
		log.info("-------ContMgmtController::downloadAllDocuments starts--------");
		DownloadAllDocumentResponse response = service.downloadAllDocuments();
		log.info("-------ContMgmtController::downloadAllDocuments returns--------");
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/document")
	public ResponseEntity<DownloadDocumentResponse> downloadDocument(@RequestBody DocumentRequest request) {
		log.info("-------ContMgmtController::downloadDocument starts--------");
		DownloadDocumentResponse response = service.downloadDocument(request);
		log.info("-------ContMgmtController::downloadDocument returns--------");
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/delete")
	public void deleteDocument(@RequestBody DocumentRequest request) {
		log.info("-------ContMgmtController::deleteDocument starts--------");
		service.deleteDocument(request);
		log.info("-------ContMgmtController::deleteDocument ends--------");
	}
	
	@PostMapping("/comment")
	public void postComment(@RequestBody PostCommentRequest request) {
		log.info("-------ContMgmtController::postComment starts--------");
		service.postComment(request);
		log.info("-------ContMgmtController::postComment ends--------");
	}

}
