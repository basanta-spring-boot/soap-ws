package com.javatechie.spring.soap.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javatechie.spring.soap.api.loaneligibility.Acknowledgement;
import com.javatechie.spring.soap.api.loaneligibility.CustomerRequest;

@Service
public class LoanEligibilityService {

	public Acknowledgement checkEligibility(CustomerRequest request) {
		Acknowledgement acknowledgement = new Acknowledgement();
		List<String> criteriaMismatchList = acknowledgement.getCriteriaMismatch();

		if (!(request.getAge() > 25 && request.getAge() <= 60)) {
			criteriaMismatchList.add("Age should be in between 25 to 60");

		}
		if (!(request.getCibilScore() > 5)) {
			criteriaMismatchList.add("Low CIBIL Score please try after 6 month");
		}

		if (!(request.getYearlyIncome() >= 200000)) {
			criteriaMismatchList.add("minimum income should be more than 200000");
		}

		if (criteriaMismatchList.size() > 0) {
			acknowledgement.setIsEligible(false);
			acknowledgement.setApprovedAmount(0);
		} else {
			acknowledgement.setIsEligible(true);
			acknowledgement.setApprovedAmount(50000);
			criteriaMismatchList.clear();
		}
		return acknowledgement;
	}
}
