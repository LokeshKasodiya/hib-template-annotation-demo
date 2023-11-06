package org.jsp.hibtemplateannotation.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.hibtemplateannotation.MerchantConfig;
import org.jsp.hibtemplateannotation.dao.MerchantDao;
import org.jsp.hibtemplateannotation.dto.Merchant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MerchantController {
		
		static Scanner sc = new Scanner(System.in);
		static ApplicationContext context;
		static MerchantDao dao;
	
	
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(MerchantConfig.class);
		dao=context.getBean(MerchantDao.class);
	}
	public static void main(String[] args) {
		System.out.println("1. Save Merchant");
		System.out.println("2. Update Merchant");
		System.out.println("3. Find Merchant by Id");
		System.out.println("4. Delete Merchant by Id");
		System.out.println("5. Find all merchants");

		switch (sc.nextInt()) {
		case 1: {
			save();
			break;
		}
		case 2: {
			update();
			break;
		}
		case 3: {
			findById();
			break;
		}
		case 4: {
			delete();
			break;
		}
		case 5: {
			findAll();
			break;
		}
		}
	}
	public static void save() {
		System.out.println("Enter the Merchant Name, phone number, email id, gst number and password to save merchant");
		Merchant m = new Merchant();
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setEmail(sc.next());
		m.setGst(sc.next());
		m.setPassword(sc.next());
		m = dao.saveMerchant(m);
		System.out.println("Merchant saved with id " + m.getId());
	}

	public static void update() {
		System.out.println("Enter the Merchant id to update merchant");
		int id = sc.nextInt();
		System.out.println("Enter the Merchant Name, phone number, email id, gst number and password to update merchant");
		Merchant m = new Merchant();
		m.setId(id);
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setEmail(sc.next());
		m.setGst(sc.next());
		m.setPassword(sc.next());
		m = dao.updateMerchant(m);
		System.out.println("Merchant updated successfully");
	}

	public static void findById() {
		System.out.println("Enter the merchant id to find merchant");
		int id = sc.nextInt();
		Merchant m = new Merchant();
		m = dao.findById(id);
		if (m != null) {
			System.out.println("Id :" + m.getId());
			System.out.println("Name :" + m.getName());
			System.out.println("Phone Number :" + m.getPhone());
			System.out.println("Email Id :" + m.getEmail());
			System.out.println("GST number :" + m.getGst());
		} else {
			System.err.println("Merchant not found with entered Id");
		}
	}

	public static void delete() {
		System.out.println("Enter the MErchant id to dekete merchant");
		int id = sc.nextInt();
		boolean e = dao.deleteMerchant(id);
		if (e) {
			System.out.println("Merchant deleted successfully");
		} else
			System.err.println("MErchant not found with entered Id");
	}

	public static void findAll() {
		List<Merchant> merchant = dao.findAll();
		if (merchant != null) {
			for (Merchant m : merchant) {
				System.out.println("Id :" + m.getId());
				System.out.println("Name :" + m.getName());
				System.out.println("Phone Number :" + m.getPhone());
				System.out.println("Email Id :" + m.getEmail());
				System.out.println("GST number :" + m.getGst());
			}
		}
	}
	
}
