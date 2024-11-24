package com.s13sh.ecommerce.service.implementation;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.s13sh.ecommerce.dto.Customer;
import com.s13sh.ecommerce.dto.Product;
import com.s13sh.ecommerce.dto.Seller;
import com.s13sh.ecommerce.helper.AES;
import com.s13sh.ecommerce.helper.MyEmailSender;
import com.s13sh.ecommerce.repository.CustomerRepository;
import com.s13sh.ecommerce.repository.ProductRepository;
import com.s13sh.ecommerce.repository.SellerRepository;
import com.s13sh.ecommerce.service.MainService;

import jakarta.servlet.http.HttpSession;

@Service
public class MainServiceImpl implements MainService {

    @Value("${admin.email}")
    private String adminEmail;

    @Autowired
    MyEmailSender emailSender;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public String loadHome(ModelMap map) {
        List<Product> products = productRepository.findByApprovedTrue();
        if (!products.isEmpty()) {
            map.put("products", products);
        }

        return "home.html";
    }

    @Override
    public String loadLogin() {
        return "login.html";
    }

    @Override
    public String login(String email, String password, HttpSession session) {
        if (email.equals(adminEmail) && password.equals(adminPassword)) {
            session.setAttribute("admin", "admin");
            session.setAttribute("success", "Login Success");
            return "redirect:/admin/home";
        } else {
            Seller seller = sellerRepository.findByEmail(email);
            Customer customer = customerRepository.findByEmail(email);

            if (seller == null && customer == null) {
                session.setAttribute("failure", "Invalid Email");
                return "redirect:/login";
            } else {
                if (seller == null) {
                    if (AES.decrypt(customer.getPassword(), "123").equals(password)) {
                        if (customer.isVerified()) {
                            session.setAttribute("customer", customer);
                            session.setAttribute("success", "Login Success");
                            return "redirect:/customer/home";
                        } else {
                            customer.setOtp(new Random().nextInt(100000, 1000000));
                            customerRepository.save(customer);
                            emailSender.sendOtp(customer);
                            session.setAttribute("success", "Otp Sent Success");
                            session.setAttribute("id", customer.getId());
                            return "redirect:/customer/otp";
                        }
                    } else {
                        session.setAttribute("failure", "Invalid Passowrd");
                        return "redirect:/login";
                    }
                } else {
                    if (AES.decrypt(seller.getPassword(), "123").equals(password)) {
                        if (seller.isVerified()) {
                            session.setAttribute("seller", seller);
                            session.setAttribute("success", "Login Success");
                            return "redirect:/seller/home";
                        } else {
                            seller.setOtp(new Random().nextInt(100000, 1000000));
                            sellerRepository.save(seller);
                            emailSender.sendOtp(seller);
                            session.setAttribute("success", "Otp Sent Success");
                            session.setAttribute("id", seller.getId());
                            return "redirect:/seller/otp";
                        }
                    } else {
                        session.setAttribute("failure", "Invalid Passowrd");
                        return "redirect:/login";
                    }
                }
            }
        }
    }

    @Override
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
		session.removeAttribute("seller");
		session.removeAttribute("customer");
		session.setAttribute("success", "Logged out Successfully");
		return "redirect:/"; 
    }

}
