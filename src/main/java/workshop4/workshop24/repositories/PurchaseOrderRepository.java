package workshop4.workshop24.repositories;

import static workshop4.workshop24.repositories.Queries.SQL_INSERT_PURCHASE_ORDER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import workshop4.workshop24.models.PurchaseOrder;


@Repository
public class PurchaseOrderRepository {

    @Autowired
    private JdbcTemplate temp;

    public boolean insertPurchaseOrder(PurchaseOrder po){

        return temp.update(SQL_INSERT_PURCHASE_ORDER, po.getOrderId(), po.getName()) >0;
    }
    
}
