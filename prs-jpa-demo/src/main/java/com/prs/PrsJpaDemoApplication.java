package com.prs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prs.db.Database;
import com.prs.db.ProductDB;
import com.prs.db.PurchaseRequestDB;
import com.prs.db.PurchaseRequestLineItemDB;
import com.prs.db.UserDB;
import com.prs.db.VendorDB;
import com.prs.logic.Product;
import com.prs.logic.PurchaseRequest;
import com.prs.logic.PurchaseRequestLineItem;
import com.prs.logic.User;
import com.prs.logic.Vendor;
import com.prs.util.Console;;

@SpringBootApplication
public class PrsJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrsJpaDemoApplication.class, args);
		
		System.out.println("Hello");
		VendorDB v = new VendorDB();
		UserDB u = new UserDB();
		ProductDB p = new ProductDB();
		PurchaseRequestDB pr = new PurchaseRequestDB();
		PurchaseRequestLineItemDB prli = new PurchaseRequestLineItemDB();
		
		System.out.println("\nUSERS");
		dbAction(u, udb -> System.out.println(u.getAll().toString()));
		
		System.out.println("\nVENDORS");
		dbAction(v, vdb -> System.out.println(v.getAll().toString()));
		
		System.out.println("\nPRODUCTS");
		dbAction(p, pdb -> System.out.println(p.getAll().toString())); 
		
		System.out.println("\nPURCHASE REQUESTS");
		dbAction(pr, prdb -> System.out.println(pr.getAll().toString()));
		
		System.out.println("\nPURCHASE REQUEST LINE ITEMS");
		dbAction(prli, prlidb -> System.out.println(prli.getAll().toString()));
		
		
		System.out.println("\n");
		
		
		
		//ADD NEW PURCHASE REQUESTS WORK IN PROGRESS... I WANT TO FINISH THIS BUT I NEED TO GO WATCH GAME OF THRONES RIGHT NOW
//		String addNew = Console.getString("Add new purchase request? (y/n) ", "y", "n");
//		
//		while (addNew.equals("y")) {
//			int userId = Console.getInt("Enter userID");
//			User thisUser = u.get(userId);
//			String description = Console.getString("Enter Descripton: ");
//			String justification = Console.getString("Enter justification: ");
//			LocalDate dateNeeded = Console.getDate("Date needed? (YYYY-MM-DD)");
//			String deliveryMode = Console.getString("Enter Delivery Mode: ");
//			LocalDateTime submittedDate = LocalDateTime.now();
//			double total = 0;
//			
//			PurchaseRequest thisPurchaseRequest = new PurchaseRequest(thisUser, description, justification, dateNeeded, deliveryMode, submittedDate);
//			pr.add(thisPurchaseRequest);
//			List<PurchaseRequest> preqs = pr.getAll();
//			int newID = preqs.parallelStream()
//				.map(PurchaseRequest::getId)
//				.reduce(0, Math::max);
//			thisPurchaseRequest.setId(newID+1);
//			
//			System.out.println("Add Items to Purchase Request:");
//			String addItems = "y";
//			while (addItems.equals("y")) {
//				int productId = Console.getInt("Enter product id: ");
//				Product thisProduct = ProductDB.get(productId);
//				int quantity = Console.getInt("Enter quantity: ");
//				System.out.println("Quantity is" + quantity);
//				
//				PurchaseRequestLineItem thisprli = new PurchaseRequestLineItem(thisPurchaseRequest, thisProduct, quantity);
//				System.out.println(thisprli.toString());
//				prli.add(thisprli);
//				total += thisProduct.getPrice() * quantity;
//				thisPurchaseRequest.setTotal(total);
//				addItems = Console.getString("Enter another lineItem? (y/n)", "y", "n");
//			}
//			pr.update(thisPurchaseRequest);
//			addNew = Console.getString("Create another purchase request? (y/n)", "y", "n");
//			
//		}
		
		
		

	}
	
	private static void dbAction(Database database, Consumer<Database> method){
		method.accept(database);
	}
	

	
	private static void delete() {
		System.out.println("!!! Delete !!!");
		int id = Console.getInt("Enter ID to Delete: ");
		
		User u = UserDB.get(id);
		if (u == null) {
			System.out.println("Invalid ID!");
		} else {
			if (UserDB.delete(u)) {
				System.out.println("Delete Succes!");
			} else {
				System.out.println("Error deleting product!");
					}
				}
				
			}
		
}
	
	


