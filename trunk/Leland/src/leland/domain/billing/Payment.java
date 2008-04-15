package leland.domain.billing;

import leland.domain.base.BaseEntity;
import leland.domain.enums.PaymentType;

public class Payment
		extends BaseEntity
{
	private PaymentType paymentType;
	private double amount;
	
	
}
