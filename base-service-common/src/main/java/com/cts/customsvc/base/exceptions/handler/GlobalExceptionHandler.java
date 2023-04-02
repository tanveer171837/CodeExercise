package com.cts.customsvc.base.exceptions.handler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cts.customsvc.base.constants.BaseConstants;
import com.cts.customsvc.base.dto.BaseResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> handleException(Exception ex, WebRequest req) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatus(BaseConstants.STATUS_FAILED);
		baseResponse.setError(ExceptionUtils.getRootCauseMessage(ex));
		return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
