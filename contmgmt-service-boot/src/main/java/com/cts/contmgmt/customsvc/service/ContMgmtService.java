package com.cts.contmgmt.customsvc.service;

import org.springframework.web.multipart.MultipartFile;

import com.cts.contmgmt.api.request.DocumentRequest;
import com.cts.contmgmt.api.request.PostCommentRequest;
import com.cts.contmgmt.api.response.DownloadAllDocumentResponse;
import com.cts.contmgmt.api.response.DownloadDocumentResponse;

public interface ContMgmtService {

	void uploadDocument(String docName, MultipartFile file, String title, String docId, String author);

	DownloadAllDocumentResponse downloadAllDocuments();

	DownloadDocumentResponse downloadDocument(DocumentRequest request);

	void deleteDocument(DocumentRequest request);

	void postComment(PostCommentRequest request);

}
