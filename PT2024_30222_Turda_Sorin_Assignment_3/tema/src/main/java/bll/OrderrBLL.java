package bll;

import model.Orderr;
import dao.OrderrDAO;

import java.util.List;
import java.util.NoSuchElementException;

public class OrderrBLL {
    private OrderrDAO orderrDAO;

    public OrderrBLL() {
        orderrDAO = new OrderrDAO();
    }

    public Orderr findOrderrById(int id) {
        Orderr cl = orderrDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return cl;
    }

    public List<Orderr> findOrderrs() {
        List<Orderr> list = orderrDAO.findAll();
        if (list == null) {
            throw new NoSuchElementException("The client table is empty!");
        }
        return list;
    }

    /**
     * Creates a new order
     * @param orderr
     * @return
     */
    public int insertOrderr(Orderr orderr) {
        return orderrDAO.insert(orderr).getId();
    }

    public int updateOrderr(Orderr orderr) {
        return orderrDAO.update(orderr).getId();
    }

    public int deleteOrderr(Orderr orderr) {
        return orderrDAO.delete(orderr).getId();
    }
}
