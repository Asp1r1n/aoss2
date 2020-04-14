package aoss.assignment.a2.merged.views;

import aoss.assignment.a2.merged.controllers.auth.AuthController;
import aoss.assignment.a2.merged.controllers.inventory.*;
import aoss.assignment.a2.merged.controllers.orders.OrderController;
import aoss.assignment.a2.merged.models.Item;
import aoss.assignment.a2.merged.models.Order;
import aoss.assignment.a2.merged.models.OrderItem;
import aoss.assignment.a2.merged.models.UserSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderAppFrame extends JFrame {
    private JPanel mainPanel;
    private JTextArea taMessage;
    private JButton submitOrderButton;
    private JTextField tfLastName;
    private JTextField tfPhone;
    private JTextField tfFirstName;
    private JTextArea taAddress;
    private JTextArea taInventory;
    private JTextArea taSelectedItem;
    private JButton btTree;
    private JButton btGenomics;
    private JButton btCultureBox;
    private JButton btSeed;
    private JButton btShrub;
    private JButton btProcessing;
    private JButton btReferenceMaterial;
    private JButton btAddItem;
    private JTextField tfServerAddress;
    private JLabel lbCost;

    private TreesController treesController;
    private ShrubsController shrubsController;
    private SeedsController seedsController;
    private CultureboxesController cultureboxesController;
    private GenomicsController genomicsController;
    private ReferencematerialsController referencematerialsController;
    private ProcessingController processingController;
    private OrderController orderController;
    private AuthController authController;

    private ArrayList<OrderItem> orderItems;
    private UserSession session;

    public OrderAppFrame(UserSession session) {
        add(mainPanel);
        setTitle("Order App Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(950, 650);

        this.session = session;
        System.out.println("Session: " + session.getToken());

        initMenu();
        initController();

        btTree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getList("TREE");
            }
        });

        btShrub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getList("SHRUB");
            }
        });

        btSeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getList("SEED");
            }
        });

        btCultureBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getList("CULTUREBOX");
            }
        });

        btGenomics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getList("GENOMICS");
            }
        });

        btProcessing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getList("PROCESSING");
            }
        });

        btReferenceMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getList("REFERENCEMATERIAL");
            }
        });

        //Add Item to Order cart
        btAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getItem();
            }
        });

        //Submit Order
        submitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postSubmitOrder();
            }
        });
    }

    private void initMenu() {
        JMenu menu = new JMenu("Menu");
        JMenuItem menuInventory = new JMenuItem("Inventory Management");
        JMenuItem menuShipping = new JMenuItem("Shipping");
        JMenuItem menuLogout = new JMenuItem("Logout");

        menu.add(menuInventory);
        menu.add(menuShipping);
        menu.add(menuLogout);

        final JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        mb.add(menu);

        menuInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                openInventoryFrame();
            }
        });

        menuShipping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //TODO: Handle shipping frame
            }
        });

        menuLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                logout();
            }
        });
    }

    private void openInventoryFrame() {
        java.awt.EventQueue.invokeLater(() -> new InventoryManagerFrame(session).setVisible(true));
        this.dispose();
    }

    private boolean logout() {
        if (tfServerAddress.getText().isEmpty()) {
            taMessage.setText("Server address should not be empty");
            return false;
        }

        authController.setServerAddress(tfServerAddress.getText());
        authController.postLogout();
        this.dispose();

        return true;
    }

    private void initController() {
        orderItems = new ArrayList<>();

        treesController = new TreesController(session);
        shrubsController = new ShrubsController(session);
        seedsController = new SeedsController(session);
        cultureboxesController = new CultureboxesController(session);
        genomicsController = new GenomicsController(session);
        processingController = new ProcessingController(session);
        referencematerialsController = new ReferencematerialsController(session);

        orderController = new OrderController(session);
        authController = new AuthController(session);
    }

    private boolean getList(String selectedCategory) {
        String result = "";

        ArrayList<Item> items = new ArrayList();

        if (tfServerAddress.getText().isEmpty()) {
            taMessage.setText("Server address should not be empty");
            return false;
        }

        if (selectedCategory != null) {
            InventoryController controller = getController(selectedCategory);
            controller.setServerAddress(tfServerAddress.getText());
            items = controller.all();
            for (Item item : items) {
                result += item.printItem(selectedCategory);
                result += "\n";
            }
        } else {
            taMessage.setText("Must select Category button");
            return false;
        }

        taInventory.setText(result);
        return true;
    }

    private InventoryController getController(String category) {
        switch (category) {
            case "TREE":
                return treesController;
            case "SHRUB":
                return shrubsController;
            case "SEED":
                return seedsController;
            case "CULTUREBOX":
                return cultureboxesController;
            case "GENOMIC":
                return genomicsController;
            case "PROCESSING":
                return processingController;
            case "REFERENCEMATERIAL":
                return referencematerialsController;
        }
        return null;
    }

    private boolean getItem() {
        String selectedItem = taInventory.getSelectedText();

        if (tfServerAddress.getText().isEmpty()) {
            taMessage.setText("Server address should not be empty");
            return false;
        }

        if (selectedItem.isEmpty()) {
            taMessage.setText("Please select whole line of item by clicking 3 times");
            return false;
        }

        if (selectedItem.contains(">>") && selectedItem.contains("::")) {
            String parseItem[] = selectedItem.split(">>");
            String category = parseItem[0];
            String itemString = parseItem[1];

            String[] categories = new String[]{"TREE", "SHRUB", "SEED", "CULTUREBOX", "GENOMIC", "PROCESSING", "REFERENCEMATERIAL"};
            List<String> list = Arrays.asList(categories);

            if (list.contains(category)) {
                InventoryController controller = getController(category);
                controller.setServerAddress(tfServerAddress.getText());

                // Here we get the trailing index and parse out the productID
                int endIndex = itemString.indexOf(":", 0);

                if (endIndex < 0) {
                    taMessage.setText("Please select whole line of item by clicking 3 times");
                    return false;
                } else {
                    String id = itemString.substring(0, endIndex);
                    Item item = controller.get(id);
                    orderItems.add(new OrderItem(item));

                    String orderList = "";
                    double totalCost = 0;
                    for (OrderItem orderItem : orderItems) {
                        orderList += orderItem.print();
                        orderList += "\n";
                        totalCost += orderItem.getItem_price();
                    }

                    lbCost.setText("$ " + totalCost);
                    taSelectedItem.setText(orderList);
                }
            }
        } else {
            taMessage.setText("Please select whole line of item by clicking 3 times");
            return false;
        }

        return true;
    }

    private boolean postSubmitOrder() {
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String address = taAddress.getText();
        String phone = tfPhone.getText();

        if (tfServerAddress.getText().isEmpty()) {
            taMessage.setText("Server address should not be empty");
            return false;
        }

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            taMessage.setText("Missing customer information!");
            return false;
        }

        if (orderItems.size() < 1) {
            taMessage.setText("Please select at least 1 item");
            return false;
        }

        //Submit new Order
        float totalCost = 0;
        for (OrderItem orderItem : orderItems) {
            totalCost += orderItem.getItem_price();
        }

        Order order = new Order(firstName, lastName, address, phone, totalCost);
        orderController.setServerAddress(tfServerAddress.getText());
        Order savedOrder = orderController.postOrder(order, orderItems);

        if (savedOrder != null) {
            //Clear array order items
            orderItems = new ArrayList<>();
            taMessage.setText("Order success");
        }

        return true;
    }
}
