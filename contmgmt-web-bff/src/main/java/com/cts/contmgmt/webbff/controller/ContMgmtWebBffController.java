package com.cts.contmgmt.webbff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.contmgmt.api.ContMgmtApiClient;
import com.cts.contmgmt.api.request.DocumentRequest;
import com.cts.contmgmt.api.request.PostCommentRequest;
import com.cts.contmgmt.api.response.DownloadAllDocumentResponse;
import com.cts.contmgmt.api.response.DownloadDocumentResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/contmgmtWebApi")
public class ContMgmtWebBffController {

	@Autowired
	private ContMgmtApiClient contMgmtApiClient;

	@ApiOperation(value = "Upload Document")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "OK"),
	        @ApiResponse(code = 400, message = "Invalid Request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 500, message = "Internal server error")})
	@PostMapping("/upload")
	public void uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
			@RequestParam("docName") String docName, @RequestParam("docId") String docId, @RequestParam("author") String author) {
		log.info("-------ContMgmtWebBffController::uploadDocument starts--------");
		contMgmtApiClient.uploadDocument(file, title, docName, docId, author);
		log.info("-------ContMgmtWebBffController::uploadDocument ends--------");
	}

	@GetMapping("/documents")
	public ResponseEntity<DownloadAllDocumentResponse> downloadAllDocuments() {
		log.info("-------ContMgmtWebBffController::downloadAllDocuments starts--------");
		ResponseEntity<DownloadAllDocumentResponse> response = contMgmtApiClient.downloadAllDocuments();
		log.info("-------ContMgmtWebBffController::downloadAllDocuments returns--------");
		return response;
	}

	@PostMapping("/document")
	public ResponseEntity<DownloadDocumentResponse> downloadDocument(@RequestBody DocumentRequest request) {
		log.info("-------ContMgmtWebBffController::downloadDocument starts--------");
		ResponseEntity<DownloadDocumentResponse> response = contMgmtApiClient.downloadDocument(request);
		log.info("-------ContMgmtWebBffController::downloadDocument returns--------");
		return response;
	}

	@PostMapping("/delete")
	public void deleteDocument(@RequestBody DocumentRequest request) {
		log.info("-------ContMgmtWebBffController::deleteDocument starts--------");
		contMgmtApiClient.deleteDocument(request);
		log.info("-------ContMgmtWebBffController::deleteDocument ends--------");
	}
	
	@PostMapping("/comment")
	public void postComment(@RequestBody PostCommentRequest request) {
		log.info("-------ContMgmtWebBffController::postComment starts--------");
		contMgmtApiClient.postComment(request);
		log.info("-------ContMgmtWebBffController::postComment ends--------");
	}

}
