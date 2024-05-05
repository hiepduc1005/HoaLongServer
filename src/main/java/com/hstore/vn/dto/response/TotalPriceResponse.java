package com.hstore.vn.dto.response;

import java.math.BigDecimal;

public class TotalPriceResponse {
	
	public BigDecimal totalPrice;


	public TotalPriceResponse(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public TotalPriceResponse() {
	}

}
