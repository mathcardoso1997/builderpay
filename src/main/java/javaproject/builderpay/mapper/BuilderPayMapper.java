package javaproject.builderpay.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import javaproject.builderpay.model.dto.BillsPaymentDto;
import javaproject.builderpay.model.entity.PaymentReceipt;
import javaproject.builderpay.model.response.RecipientResponse;

@Component
public class BuilderPayMapper {

	public BillsPaymentDto toPaymentSlipResponse(RecipientResponse recipientResponse, PaymentReceipt paymentReceipt) {
		var response = new BillsPaymentDto();
		response.setOriginalAmount(recipientResponse.getAmount());
		response.setAmount(paymentReceipt.getTotalAmount());
		response.setDueDate(recipientResponse.getDueDate());
		response.setFineAmountCalculated(paymentReceipt.getFineAmount());
		response.setInterestAmountCalculated(paymentReceipt.getInterestAmount());
		return response;
	}

	public PaymentReceipt toPaymentReceipt(RecipientResponse recipientResponse, LocalDate dtPaymentDate, LocalDate dtDueDate) {
		var interestAmount = ((dtDueDate.toEpochDay() - dtPaymentDate.toEpochDay()) * 0.00033) * recipientResponse.getAmount();
		var fineAmount = 0.02 * recipientResponse.getAmount();
		var totalAmount = recipientResponse.getAmount() + fineAmount + interestAmount;
		
		var paymentReceipt = new PaymentReceipt();
		paymentReceipt.setOriginalAmount(recipientResponse.getAmount());
		paymentReceipt.setFineAmount(fineAmount);
		paymentReceipt.setInterestAmount(interestAmount);
		paymentReceipt.setTotalAmount(totalAmount);
		paymentReceipt.setCode(recipientResponse.getCode());
		paymentReceipt.setDuetDate(dtDueDate);
		paymentReceipt.setPaymentDate(dtPaymentDate);
		paymentReceipt.setName(recipientResponse.getRecipientName());
		paymentReceipt.setRecipientDocument(recipientResponse.getRecipientDocument());
		paymentReceipt.setType(recipientResponse.getType());
		return paymentReceipt;
	}
    
}
