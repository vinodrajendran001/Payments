package SC13Project.Milestone1.Payment;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

//Please do not change the name of the package or this interface
public interface PaymentWS {

	/**
	 * Query the balance of an account
	 * @param accountID 
	 * @return balance of the account
	 * @throws JAXBException 
	 * @throws FileNotFoundException 
	 * 
	 * 
	 */
	public int queryAccount(String accountID) throws JAXBException, FileNotFoundException;
	
	/**
	 * Transfer money from account1 to account2
	 * @param accountID1 payee
	 * @param accountID2 benefiter
	 * @param amount the amount of money transfered from account1 to account2
	 * @return true if the transaction is successful; otherwise false;
	 * 
	 * 
	 */
	public boolean transfer(String accountID1, String accountID2, int amount) throws TransactionException;
		
}
