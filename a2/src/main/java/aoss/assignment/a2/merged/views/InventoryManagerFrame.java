package aoss.assignment.a2.merged.views;


import aoss.assignment.a2.merged.controllers.auth.AuthController;
import aoss.assignment.a2.merged.controllers.inventory.*;
import aoss.assignment.a2.merged.models.Item;
import aoss.assignment.a2.merged.models.UserSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryManagerFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField tfServerAddress;
    private JTextArea taResult;
    private JButton listInventoryButton;
    private JButton deleteItemButton;
    private JButton decrementButton;
    private JRadioButton rdTrees;
    private JRadioButton rdSeeds;
    private JRadioButton rdShurbs;
    private JRadioButton rdReferencematerials;
    private JRadioButton rdCultureboxes;
    private JRadioButton rdGenomics;
    private JRadioButton rdProcessing;
    private JTextField tfQuantity;
    private JTextField tfPrice;
    private JTextField tfDescription;
    private JTextField tfId;
    private JButton btAddItem;
    private JPanel panelResult;

    private TreesController treesController;
    private ShrubsController shrubsController;
    private SeedsController seedsController;
    private CultureboxesController cultureboxesController;
    private GenomicsController genomicsController;
    private ReferencematerialsController referencematerialsController;
    private ProcessingController processingController;
    private AuthController authController;

    private UserSession session;

    public InventoryManagerFrame(UserSession session) {
        add(mainPanel);
        setTitle("New Invetory Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(950, 650);

        this.session = session;
        System.out.println("Session: " + session.getToken());

        initMenu();
        initController();

        listInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getList();
            }
        });

        btAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                postItem();
            }
        });
        decrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateItem();
            }
        });
        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteItem();
            }
        });
    }

    private void initMenu() {
        JMenu menu = new JMenu("Menu");
        JMenuItem menuOrder = new JMenuItem("Order Management");
        JMenuItem menuShipping = new JMenuItem("Shipping");
        JMenuItem menuLogout = new JMenuItem("Logout");

        menu.add(menuOrder);
        menu.add(menuShipping);
        menu.add(menuLogout);

        final JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        mb.add(menu);

        menuOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                openOrderFrame();
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

    private void openOrderFrame() {
        java.awt.EventQueue.invokeLater(() -> new OrderAppFrame(session).setVisible(true));
        this.dispose();
    }

    private boolean logout() {
        if (tfServerAddress.getText().isEmpty()) {
            taResult.setText("Server address should not be empty");
            return false;
        }

        authController.setServerAddress(tfServerAddress.getText());
        authController.postLogout();
        this.dispose();

        return true;
    }

    private void initController() {
        treesController = new TreesController(session);
        shrubsController = new ShrubsController(session);
        seedsController = new SeedsController(session);
        cultureboxesController = new CultureboxesController(session);
        genomicsController = new GenomicsController(session);
        processingController = new ProcessingController(session);
        referencematerialsController = new ReferencematerialsController(session);
        authController = new AuthController(session);
    }

    private void getList() {
        String selectedCategory = getSelectedCategory();
        String result = "";

        ArrayList<Item> items = new ArrayList();
        if (!tfServerAddress.getText().isEmpty()) {
            if (selectedCategory != null) {
                InventoryController controller = getController(selectedCategory);
                controller.setServerAddress(tfServerAddress.getText());

                items = controller.all();
                for (Item item : items) {
                    result += item.printItem(selectedCategory);
                    result += "\n";
                }
            } else {
                result = "Must select Category button";
            }
        } else {
            result = "Server address should not be empty";
        }

        taResult.setText(result);
    }

    private boolean postItem() {
        String id, description;
        int quantity;
        double price;

        if (tfServerAddress.getText().isEmpty()) {
            taResult.setText("Server address should not be empty");
            return false;
        }

        if (!tfId.getText().isEmpty()) {
            id = tfId.getText();
        } else {
            taResult.setText("Must enter ID");
            return false;
        }

        if (!tfQuantity.getText().isEmpty()) {
            try {
                quantity = Integer.parseInt(tfQuantity.getText());
            } catch (NumberFormatException e) {
                taResult.setText("Quantity should be an integer");
                return false;
            }
        } else {
            taResult.setText("Must enter Quantity");
            return false;
        }

        if (!tfPrice.getText().isEmpty()) {
            try {
                price = Double.parseDouble(tfPrice.getText());
            } catch (NumberFormatException e) {
                taResult.setText("Price should be an double");
                return false;
            }
        } else {
            taResult.setText("Must enter Price");
            return false;
        }

        if (!tfDescription.getText().isEmpty()) {
            description = tfDescription.getText();
        } else {
            taResult.setText("Must enter Description");
            return false;
        }

        String category = getSelectedCategory();
        InventoryController controller = getController(category);
        controller.setServerAddress(tfServerAddress.getText());

        if (controller != null) {
            controller.create(new Item(id, description, quantity, price));
        } else {
            taResult.setText("Must select Category button");
            return false;
        }


        taResult.setText("Input data success!");
        return true;
    }

    private boolean updateItem() {
        String selectedItem = taResult.getSelectedText();

        if (tfServerAddress.getText().isEmpty()) {
            taResult.setText("Server address should not be empty");
            return false;
        }

        if (selectedItem.isEmpty()) {
            taResult.setText("Please select whole line of item by clicking 3 times");
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
                    taResult.setText("Please select whole line of item by clicking 3 times");
                    return false;
                } else {
                    String id = itemString.substring(0, endIndex);
                    Item item = controller.get(id);
                    item.setQuantity(item.getQuantity() - 1);

                    controller.update(id, item);
                    taResult.setText("Decrement Item success");
                }
            }
        } else {
            taResult.setText("Please select whole line of item by clicking 3 times");
            return false;
        }

        return true;
    }

    private boolean deleteItem() {
        String selectedItem = taResult.getSelectedText();

        if (tfServerAddress.getText().isEmpty()) {
            taResult.setText("Server address should not be empty");
            return false;
        }

        if (selectedItem.isEmpty()) {
            taResult.setText("Please select whole line of item by clicking 3 times");
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
                    taResult.setText("Please select whole line of item by clicking 3 times");
                    return false;
                } else {
                    String id = itemString.substring(0, endIndex);
                    controller.delete(id);
                    taResult.setText("Delete Item success");
                }
            }
        } else {
            taResult.setText("Please select whole line of item by clicking 3 times");
            return false;
        }

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

    private String getSelectedCategory() {
        String result = null;

        if (rdTrees.isSelected()) {
            result = "TREE";
        }

        if (rdShurbs.isSelected()) {
            result = "SHRUB";
        }

        if (rdSeeds.isSelected()) {
            result = "SEED";
        }

        if (rdCultureboxes.isSelected()) {
            result = "CULTUREBOX";
        }

        if (rdGenomics.isSelected()) {
            result = "GENOMIC";
        }

        if (rdProcessing.isSelected()) {
            result = "PROCESSING";
        }

        if (rdReferencematerials.isSelected()) {
            result = "REFERENCEMATERIAL";
        }
        return result;
    }
}
