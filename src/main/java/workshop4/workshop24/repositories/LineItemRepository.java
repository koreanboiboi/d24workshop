package workshop4.workshop24.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import workshop4.workshop24.models.LineItem;

import static workshop4.workshop24.repositories.Queries.*;

@Repository
public class LineItemRepository {

    @Autowired
    private JdbcTemplate temp;

    public void addAllLineItem(List <LineItem> items, String orderId){

        List<Object[]> data = items.stream()
                                .map(li -> {
                                    Object[] i = new Object[3];
                                    i[0] = li.getDescription();
                                    i[1] = li.getQuantity();
                                    i[2] = orderId;
                                    return i;
                                } ).toList();

        temp.batchUpdate(SQL_INSERT_LINE_ITEM, data);

    }
    
}
