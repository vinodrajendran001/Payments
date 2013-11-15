package SC13Project.Milestone1.Payment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import SC13Project.Milestone1.Payment.Database.AccountInfo;
import SC13Project.Milestone1.Payment.Database.BanKInfo;
import SC13Project.Milestone1.Payment.Database.ObjectFactory;

//Please do not change the name of the package or this interface
//Please add here your implementation
public class PaymentImpl implements PaymentWS {

	List<AccountInfo> accInfo;
	List<BanKInfo> bankInfo;
	int balance;
	int flag = 0;
	int balance_Left;
	AccountInfo account_info;
	BanKInfo bi;
	
	@Override
	public int queryAccount(String accountID) throws JAXBException, FileNotFoundException
	{
		// TODO Auto-generated method stub
		 String packageName = AccountInfo.class.getPackage().getName();
         JAXBContext jc = JAXBContext.newInstance(packageName);
         Unmarshaller u = jc.createUnmarshaller();
         accInfo = new ArrayList<AccountInfo>();
         @SuppressWarnings("unchecked")
         JAXBElement<AccountInfo> root = (JAXBElement<AccountInfo>) u.unmarshal(new FileInputStream("bankDB.xml"));
         AccountInfo account = root.getValue();
         accInfo = bi.getAccount();
         ListIterator<AccountInfo> acc_ril = accInfo.listIterator();
         while (acc_ril.hasNext())
         {
        	 AccountInfo acc = acc_ril.next();
        	 if(accountID == acc.getAccountID())
        	 {
        		 balance = acc.getAmount();
        	 }
         }
        return balance;
	}

	@Override
	public boolean transfer(String accountID1, String accountID2, int amount)
			throws TransactionException {
		
		// TODO Auto-generated method stub
		 accInfo = bi.getAccount();
		 ListIterator<AccountInfo> acc_ril = accInfo.listIterator();
		 while (acc_ril.hasNext())
         {
			 AccountInfo acc = acc_ril.next();
        	 if(accountID1 == acc.getAccountID())
        	 {
        		 balance_Left = acc.getAmount()-amount;
        		 if(balance_Left > 0)
        		 {
        		 acc.setAmount(balance_Left);
        		 flag = 1;
        		 }
        	 }
        	 else if(accountID2 == acc.getAccountID() && flag == 1)
        	 {
        		 acc.setAmount(acc.getAmount()+amount);
        		 return true;
        	 }
         }
		return false;
	}

}
