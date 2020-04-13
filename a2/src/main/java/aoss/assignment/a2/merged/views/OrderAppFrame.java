package aoss.assignment.a2.merged.views;

import aoss.assignment.a2.merged.controllers.inventory.*;
import aoss.assignment.a2.merged.controllers.orders.OrderController;
import aoss.assignment.a2.merged.models.Item;
import aoss.assignment.a2.merged.models.Order;
import aoss.assignment.a2.merged.models.OrderItem;

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

    private ArrayList<OrderItem> orderItems;

    public OrderAppFrame() {
        add(mainPanel);
        setTitle("Order App Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 650);

        initController();
        initComponent();

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

    private void initController() {
        orderItems = new ArrayList<>();

        treesController = new TreesController();
        shrubsController = new ShrubsController();
        seedsController = new SeedsController();
        cultureboxesController = new CultureboxesController();
        genomicsController = new GenomicsController();
        processingController = new ProcessingController();
        referencematerialsController = new ReferencematerialsController();

        orderController = new OrderController();
    }

    private void initComponent() {

    }

    private boolean getList(String selectedCategory) {
        String result = "";

        ArrayList<Item> items = new ArrayList();

        if (selectedCategory != null) {
            InventoryController controller = getController(selectedCategory);
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
        Order savedOrder = orderController.postOrder(order, orderItems);

        if (savedOrder != null) {
            //Clear array order items
            orderItems = new ArrayList<>();
            taMessage.setText("Order success");
        }

        return true;
    }
}
