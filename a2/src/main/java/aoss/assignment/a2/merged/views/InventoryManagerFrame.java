package aoss.assignment.a2.merged.views;


import aoss.assignment.a2.merged.controllers.inventory.*;
import aoss.assignment.a2.merged.models.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private TreesController treesController;
    private ShrubsController shrubsController;
    private SeedsController seedsController;
    private CultureboxesController cultureboxesController;
    private GenomicsController genomicsController;
    private ReferencematerialsController referencematerialsController;
    private ProcessingController processingController;

    public InventoryManagerFrame() {
        add(mainPanel);
        setTitle("New Invetory Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 650);

        initComponent();
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
                //TODO: Handle order frame
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
                //TODO: Handle logout frame
            }
        });
    }

    private void initComponent() {
        //TODO: Handle Component

    }

    private void initController() {
        treesController = new TreesController();
        shrubsController = new ShrubsController();
        seedsController = new SeedsController();
        cultureboxesController = new CultureboxesController();
        genomicsController = new GenomicsController();
        processingController = new ProcessingController();
        referencematerialsController = new ReferencematerialsController();
    }

    private void getList() {
        String selectedCategory = null;
        String result = "";

        ArrayList<Item> items = new ArrayList();
        if (rdTrees.isSelected()) {
            items = treesController.all();
            selectedCategory = "TREE";
        }

        if (rdShurbs.isSelected()) {
            items = shrubsController.all();
            selectedCategory = "SHURB";
        }

        if (rdSeeds.isSelected()) {
            items = seedsController.all();
            selectedCategory = "SEED";
        }

        if (rdCultureboxes.isSelected()) {
            items = cultureboxesController.all();
            selectedCategory = "CULTUREBOX";
        }

        if (rdGenomics.isSelected()) {
            items = genomicsController.all();
            selectedCategory = "GENOMIC";
        }

        if (rdProcessing.isSelected()) {
            items = processingController.all();
            selectedCategory = "PROCESSING";
        }

        if (rdReferencematerials.isSelected()) {
            items = referencematerialsController.all();
            selectedCategory = "REFERENCEMATERIAL";
        }

        if (selectedCategory != null) {
            for (Item item : items) {
                result += item.printItem(selectedCategory);
                result += "\n";
            }
        } else {
            result = "Must select Category button";
        }

        taResult.setText(result);
    }

    private boolean postItem() {
        String id, description;
        int quantity;
        double price;

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

        InventoryController controller = getSelectedController();
        if (controller != null) {
            controller.create(new Item(id, description, quantity, price));
        } else {
            taResult.setText("Must select Category button");
            return false;
        }


        taResult.setText("Input data success!");
        return true;
    }

    private InventoryController getSelectedController() {
        if (rdTrees.isSelected()) {
            return treesController;
        }

        if (rdShurbs.isSelected()) {
            return shrubsController;
        }

        if (rdSeeds.isSelected()) {
            return seedsController;
        }

        if (rdCultureboxes.isSelected()) {
            return cultureboxesController;
        }

        if (rdGenomics.isSelected()) {
            return genomicsController;
        }

        if (rdProcessing.isSelected()) {
            return processingController;
        }

        if (rdReferencematerials.isSelected()) {
            return referencematerialsController;
        }

        return null;
    }

    private String getSelectedTag() {
        String result = null;

        if (rdTrees.isSelected()) {
            result = "TREE";
        }

        if (rdShurbs.isSelected()) {
            result = "SHURB";
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

    public static void main(String[] args) {
        InventoryManagerFrame frame = new InventoryManagerFrame();
        frame.setVisible(true);
    }
}
