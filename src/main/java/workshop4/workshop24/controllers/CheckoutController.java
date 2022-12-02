package workshop4.workshop24.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import workshop4.workshop24.exceptions.OrderException;
import workshop4.workshop24.models.LineItem;
import workshop4.workshop24.models.PurchaseOrder;
import workshop4.workshop24.services.OrderService;

@Controller
@RequestMapping(path="/checkout")
public class CheckoutController {

    @Autowired
    private OrderService ordSvc;
    
    @PostMapping
    public String postCheckout(Model model, HttpSession sess)throws OrderException{

     

       PurchaseOrder po = (PurchaseOrder) sess.getAttribute("checkoutcart");
       List<LineItem> item = (List<LineItem>) po.getLineItems();
       sess.invalidate();
       ordSvc.createNewOrder(po);
       model.addAttribute("total", item.size());


        return "checkout";

    }
    
}
