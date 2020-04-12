package aoss.assignment.a2.merged.views;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManagerFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField tfServerAddress;
    private JTextArea taResult;
    private JButton listInventoryButton;
    private JButton deleteItemButton;
    private JButton decrementButton;
    private JRadioButton rdTrees;
    private JRadioButton rdSeeds;
    private JRadioButton rdShu;
    private JRadioButton referenceMaterialsRadioButton;
    private JRadioButton cultureBoxesRadioButton;
    private JRadioButton geonomicsRadioButton;
    private JRadioButton processingRadioButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField1;
    private JButton addItemButton;

    public InventoryManagerFrame() {
        add(mainPanel);
        setTitle("New Invetory Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 650);

        initComponent();
        initMenu();
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

    public static void main(String[] args) {
        InventoryManagerFrame frame = new InventoryManagerFrame();
        frame.setVisible(true);
    }
}
