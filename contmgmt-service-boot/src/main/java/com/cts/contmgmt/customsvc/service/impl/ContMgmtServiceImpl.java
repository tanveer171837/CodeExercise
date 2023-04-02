package com.cts.contmgmt.customsvc.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cts.contmgmt.api.IPlaceholderService;
import com.cts.contmgmt.api.placeholder.Post;
import com.cts.contmgmt.api.request.DocumentRequest;
import com.cts.contmgmt.api.request.PostCommentRequest;
import com.cts.contmgmt.api.response.DocumentDTO;
import com.cts.contmgmt.api.response.DownloadAllDocumentResponse;
import com.cts.contmgmt.api.response.DownloadDocumentResponse;
import com.cts.contmgmt.customsvc.entity.Document;
import com.cts.contmgmt.customsvc.helper.DocumentHelper;
import com.cts.contmgmt.customsvc.respository.ContMgmtRepository;
import com.cts.contmgmt.customsvc.service.ContMgmtService;
import com.cts.customsvc.base.exceptions.AppException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContMgmtServiceImpl implements ContMgmtService {

	@Autowired
	private ContMgmtRepository repository;

	@Autowired
	private DocumentHelper helper;

	@Autowired
	private IPlaceholderService placeholderService;

	@Override
	@Transactional
	public void uploadDocument(String docName, MultipartFile file, String title, String docId, String author) {
		log.info("------ContMgmtServiceImpl::uploadDocument starts-------- ");
		Document document = null;
		try {
			if (StringUtils.isNotBlank(docId)) {
				Optional<Document> documentOptional = repository.findById(Long.valueOf(docId));
				if (documentOptional.isPresent()) {
					document = documentOptional.get();
					helper.updateDocumentUploadEntity(document, docName, file);
				}
			} else {
				document = repository.save(helper.buildDocumentUploadEntity(docName, file, author));
				placeholderService.addPost(helper.buildDocumentPost(document.getId().intValue(), title, author));
			}
		} catch (Exception e) {
			log.error("------Exception occured in ContMgmtServiceImpl::uploadDocument-------- ", e);
			throw new AppException(e);
		}
		log.info("------ContMgmtServiceImpl::uploadDocument ends-------- ");

	}

	@Override
	public DownloadAllDocumentResponse downloadAllDocuments() {
		log.info("------ContMgmtServiceImpl::uploadDocument starts-------- ");
		DownloadAllDocumentResponse response = DownloadAllDocumentResponse.builder().build();
		List<DocumentDTO> documents = null;
		try {
			List<Document> documentList = repository.findAll();
			if (!CollectionUtils.isEmpty(documentList)) {
				documents = helper.buildDownloadAllDocuments(documentList);
			} else {
				documents = Collections.emptyList();
			}
		} catch (Exception e) {
			log.error("------Exception occured in ContMgmtServiceImpl::downloadAllDocuments-------- ", e);
			throw new AppException(e);
		}
		response.setDocuments(documents);
		log.info("------ContMgmtServiceImpl::uploadDocument returns-------- ");
		return response;

	}

	@Override
	public DownloadDocumentResponse downloadDocument(DocumentRequest request) {
		log.info("------ContMgmtServiceImpl::uploadDocument starts-------- ");
		DownloadDocumentResponse response = DownloadDocumentResponse.builder().build();
		try {
			Optional<Document> documentOptional = repository.findById(request.getDocId());
			if (documentOptional.isPresent()) {
				DocumentDTO dto = helper.buildDownloadDocument(documentOptional.get());
				Post post = placeholderService.getPost(String.valueOf(documentOptional.get().getId()),
						documentOptional.get().getCreatedBy());
				if (Objects.nonNull(post)) {
					post.setComments(placeholderService.getComment(String.valueOf(documentOptional.get().getId())));
					dto.setPost(post);
				}
				response.setDocumentDTO(dto);
			}
		} catch (Exception e) {
			log.error("------Exception occured in ContMgmtServiceImpl::downloadDocument-------- ", e);
			throw new AppException(e);
		}
		log.info("------ContMgmtServiceImpl::downloadDocument returns-------- ");
		return response;
	}

	@Override
	public void deleteDocument(DocumentRequest request) {
		log.info("------ContMgmtServiceImpl::deleteDocument starts-------- ");
		try {
			repository.deleteById(request.getDocId());
		} catch (Exception e) {
			log.error("------Exception occured in ContMgmtServiceImpl::deleteDocument--------", e);
			throw new AppException(e);
		}
		log.info("------ContMgmtServiceImpl::deleteDocument ends-------- ");
	}

	@Override
	public void postComment(PostCommentRequest request) {
		log.info("------ContMgmtServiceImpl::postComment starts-------- ");
		try {
			placeholderService.postComment(
					helper.buildDocumentComment(request.getDocId(), request.getComments(), request.getCommentBy()));
		} catch (Exception e) {
			log.error("------Exception occured in ContMgmtServiceImpl::postComment--------", e);
			throw new AppException(e);
		}
		log.info("------ContMgmtServiceImpl::postComment ends-------- ");
	}

}
