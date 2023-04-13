
package javaproject.builderpay.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaproject.builderpay.model.dto.BillsPaymentDto;
import javaproject.builderpay.model.request.BillsPaymentRequest;
import javaproject.builderpay.service.BuilderPayService;


@CrossOrigin
@RestController
@RequestMapping(value= "/builder-pay")
public class BuilderPayController {

	@Autowired
    private BuilderPayService service;
	
	@PostMapping(value = "/bills-payment", consumes = MediaType. APPLICATION_JSON_VALUE, produces = MediaType. APPLICATION_JSON_VALUE)
    public ResponseEntity<BillsPaymentDto> billsPayment(@Valid @RequestBody BillsPaymentRequest request){
		try {
			var response = this.service.calculateAmountCharged(request);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
	
}
