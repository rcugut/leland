package leland.domain.billing;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

import leland.domain.Client;
import leland.domain.base.BaseEntity;
import leland.domain.enums.PaymentType;

@Entity
public class Payment
		extends BaseEntity
{
	protected Client client;
	
	protected PaymentType paymentType;
	protected double amount;
	
	protected Map<String, String> details = new HashMap<String, String>();
	
}
