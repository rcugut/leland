package leland.domain.billing.client;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import leland.domain.Client;
import leland.domain.base.BaseEntity;
import leland.domain.enums.PaymentType;

@Entity
@Table(name="LND_CLIENT_PAYMENT")
public final class ClientPayment
		extends BaseEntity
{
	protected Client client;
	
	protected PaymentType paymentType;
	protected double amount;
	
	protected Map<String, String> details = new HashMap<String, String>();
	
}
