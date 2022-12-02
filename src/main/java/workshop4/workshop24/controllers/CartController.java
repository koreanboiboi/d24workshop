package workshop4.workshop24.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import workshop4.workshop24.exceptions.OrderException;
import workshop4.workshop24.models.LineItem;
import workshop4.workshop24.models.PurchaseOrder;

@Controller

@RequestMapping(path = "/cart")
public class CartController {

    @PostMapping
    public String postCart (@RequestBody MultiValueMap<String, String> form, HttpSession sess, Model model) throws OrderException {

        List<LineItem> li = (List<LineItem>) sess.getAttribute("cart");

        if( li == null){
            li= new LinkedList<>();
            sess.setAttribute("cart", li);
        }

        String item = form.getFirst("item");
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        li.add(new LineItem(item, quantity));
        PurchaseOrder po = new PurchaseOrder();
        po.setName(form.getFirst("name"));
        po.setLineItems(li);

        sess.setAttribute("checkoutcart", po);
        model.addAttribute("lineItems", li);

        return "cart_template";
    }
    
}
