package bll;

import model.Client;
import dao.ClientDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 * Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */


public class ClientBLL {
    private ClientDAO clientDAO;

    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client cl = clientDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return cl;
    }

    /**
     * Invoke findAll method to get all clients from the client table
     * @return List of clients
     */
    public List<Client> findClients() {
        List<Client> list = clientDAO.findAll();
        if (list == null) {
            throw new NoSuchElementException("The client table is empty!");
        }
        return list;
    }

    /**
     * Inserts a new item in the client table
     * @param client
     * @return client id
     */
    public int insertClient(Client client) {
        return clientDAO.insert(client).getId();
    }

    /**
     * Updates data for a client
     * @param client
     * @return client id
     */
    public int updateClient(Client client) {
        return clientDAO.update(client).getId();
    }

    /**
     * Deletes a client form the table
     * @param client
     * @return
     */
    public int deleteClient(Client client) {
        return clientDAO.delete(client).getId();
    }
}
