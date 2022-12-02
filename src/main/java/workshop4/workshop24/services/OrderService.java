package workshop4.workshop24.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import workshop4.workshop24.exceptions.OrderException;
import workshop4.workshop24.models.PurchaseOrder;
import workshop4.workshop24.repositories.LineItemRepository;
import workshop4.workshop24.repositories.PurchaseOrderRepository;

@Service
public class OrderService {

    @Autowired
    private PurchaseOrderRepository poRepo;
    @Autowired
    private LineItemRepository liRepo;

    @Transactional (rollbackFor = OrderException.class)
    public void createNewOrder(PurchaseOrder po) throws OrderException {
       String orderId = UUID.randomUUID().toString().substring(0,8);
       System.out.println("order ID:" + orderId);
       po.setOrderId(orderId);
       poRepo.insertPurchaseOrder(po);
       if(po.getLineItems().size() > 5)
            throw new OrderException("Cannot order > 5 items !");
       liRepo.addAllLineItem(po.getLineItems(), orderId);
    }

}
