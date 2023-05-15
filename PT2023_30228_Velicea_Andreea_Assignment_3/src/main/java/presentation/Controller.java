package presentation;

import bussinessLogic.BillBLL;
import bussinessLogic.ClientBLL;
import bussinessLogic.OrderBLL;
import bussinessLogic.ProductBLL;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care controleaza intreaga aplicatie. Aici sunt implementate rolurile butoanelor.
 */
public class Controller {
    private View view;
    private ClientView clientView = new ClientView();
    private ProductView productView = new ProductView();
    private OrderView orderView = new OrderView();
    private BillView billView = new BillView();


    /**
     * Constructoul clasei, in care se adauga actionListener pe toate butoanele si sunt sincronizate toate cele 4 ferestre
     *
     * @param view
     */
    public Controller(final View view) {
        this.view = view;

        /**
         * Aici este implementata logica pentru interfata client
         */
        this.view.clientShow(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                clientView.setVisible(true);
                /**
                 * insereaza un client in baza de date
                 */
                clientView.addClientListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Client client = new Client(clientView.getIdClient(), clientView.getEmailClient(), clientView.getFirstName(), clientView.getLastName());
                        ClientBLL clientBLL = new ClientBLL();
                        try {
                            if (ClientBLL.findClientById(client.getId()) != null) {
                                JOptionPane.showMessageDialog(null, "acest id exista deja");
                            }
                        } catch (Exception ex) {
                            ClientBLL.insertClient(client);
                        }
                    }
                });
                /**
                 * modifica un client din baza de date
                 */
                clientView.updateClientListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Client client = new Client(clientView.getIdClient(), clientView.getEmailClient(), clientView.getFirstName(), clientView.getLastName());
                        ClientBLL.updateClientById(client);
                    }
                });

                /**
                 * sterge un client din baza de date
                 */
                clientView.deleteClientListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ClientBLL.deleteClientById(clientView.getIdClient());
                    }
                });
                /**
                 * arata toti clienti din baza de date
                 */
                clientView.showTableClientListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Object> list = new ArrayList<>();
                        List<Client> clients = new ArrayList<>();
                        clients = ClientBLL.findClientAll();
                        for (Client c : clients) {
                            list.add(c);
                        }
                        TableUsingReflection(list, clientView.getTable());
                    }
                });
                /**
                 * intoarcerea la pagina principala
                 */
                clientView.backListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clientView.setVisible(false);
                        view.setVisible(true);
                    }
                });
                /**
                 * curatarea casetelor
                 */
                clientView.clearListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clientView.clear();
                    }
                });
            }
        });
        /**
         * Aici este implementata logica pentru interfata product
         */
        this.view.productShow(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                productView.setVisible(true);

                /**
                 * insereaza un produs in baza de date
                 */
                productView.addProductListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Product product = new Product(productView.getIdProduct(), productView.getNameProduct(), productView.getPriceProduct(), productView.getStocProduct());
                        try {
                            if (ProductBLL.findProductById(product.getId()) != null) {
                                JOptionPane.showMessageDialog(null, "acest id exista deja");
                            }
                        } catch (Exception ex) {
                            ProductBLL.insertProduct(product);
                        }
                    }
                });
                /**
                 * modifica un produs din baza de date
                 */
                productView.updateProductListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Product product = new Product(productView.getIdProduct(), productView.getNameProduct(), productView.getPriceProduct(), productView.getStocProduct());
                        ProductBLL.updateProductById(product);
                    }
                });
                /**
                 * sterge un produs din baza de date
                 */
                productView.deleteProductListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ProductBLL.deleteProductById(productView.getIdProduct());
                    }
                });
                /**
                 * arata produsele din baza de date
                 */
                productView.showProductTableListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Object> list = new ArrayList<>();
                        List<Product> products = new ArrayList<>();
                        products = ProductBLL.findProductAll();
                        for (Product p : products) {
                            list.add(p);
                        }
                        TableUsingReflection(list, productView.getTable());
                    }
                });
                /**
                 * intoarcerea la fereastra principala
                 */
                productView.backListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.setVisible(true);
                        productView.setVisible(false);
                    }
                });
                /**
                 * curatarea casetelor
                 */
                productView.clearListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        productView.clear();
                    }
                });
            }
        });
        /**
         * Aici este implementata logica pentru interfata order
         */
        this.view.orderShow(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                orderView.setVisible(true);
                orderView.comboIdClient();
                orderView.comboIdProduct();
                /**
                 * insereaza o comanda in baza de date
                 */
                orderView.addOrderListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Order order = new Order(orderView.getIdOrder(), orderView.getClientId(), orderView.getProductId(), orderView.getQuantity());
                        OrderBLL orderBLL = new OrderBLL();
                        try {
                            if (OrderBLL.findOrderById(order.getOrderId()) != null) {
                                JOptionPane.showMessageDialog(null, "acest id exista deja");
                            }
                        } catch (Exception ex) {
                            try {
                                OrderBLL.insertOrders(order);
                            } catch (Exception exc) {
                                throw new RuntimeException(exc);
                            }
                        }
                    }
                });
                /**
                 * sterge o comanda din baza de date
                 */
                orderView.deleteOrderListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Order order = new Order(orderView.getIdOrder(), orderView.getClientId(), orderView.getProductId(), orderView.getQuantity());
                        try {
                            OrderBLL.deleteOrder(order);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                /**
                 * arata comenzile din baza de date
                 */
                orderView.showTableOrderListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Object> list = new ArrayList<>();
                        List<Order> orders = new ArrayList<>();
                        orders = OrderBLL.findOrdersAll();
                        for (Order o : orders) {
                            list.add(o);
                        }
                        TableUsingReflection(list, orderView.getTable());
                    }
                });
                /**
                 * intoarcerea la fereastra principala
                 */
                orderView.backListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.setVisible(true);
                        orderView.setVisible(false);
                    }
                });
                /**
                 * curatarea casetelor
                 */
                orderView.clearListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        orderView.clear();
                    }
                });
            }
        });
        /**
         * Aici este implementata logica pentru interfata bill
         */
        this.view.billShow(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                billView.setVisible(true);
                /**
                 * arata facturile din baza de date
                 */
                billView.showTable(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Object> list = new ArrayList<>();
                        List<Bill> bills = new ArrayList<>();
                        bills = BillBLL.showAllBils();
                        for (Bill b : bills) {
                            list.add(b);
                        }
                        TableUsingReflection(list, billView.getTable());
                    }
                });
                /**
                 * Inapoi la pagina principala
                 */
                billView.backListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        billView.setVisible(false);
                        view.setVisible(true);
                    }
                });
            }
        });
    }


    /**
     * Metoda care utilizeaza tehnica Reflection. Aici este implementata logica care aduga elementele din baza de date in tabel.
     *
     * @param list
     * @param table
     */
    private void TableUsingReflection(List<Object> list, JTable table) {
        DefaultTableModel tableModel = new DefaultTableModel();
        if (list.size() != 0) {
            Object obj = list.get(0);
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    tableModel.addColumn(field.getName());
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
            }
            for (Object o : list) {
                List<Object> attr = new ArrayList<>();
                for (Field field : o.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value;
                    try {
                        value = field.get(o);
                        attr.add(value);
                    } catch (IllegalArgumentException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
                tableModel.addRow(attr.toArray());
            }
            table.setModel(tableModel);
        } else {
            JOptionPane.showMessageDialog(null, "tabelul este gol!");
            table.setModel(tableModel);
        }
    }


}