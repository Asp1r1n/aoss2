package aoss.assignment.a2.drivers.run;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 11.04.2020 */

import aoss.assignment.a2.eeps.apps.inventory.InventoryMainFrame;
import aoss.assignment.a2.eeps.apps.order.OrderMainFrame;
import aoss.assignment.a2.eeps.apps.shipping.ShippingMainFrame;
import aoss.assignment.a2.leaftech.inventory.InventoryManagerFrame;
import aoss.assignment.a2.merged.views.LoginFrame;
import aoss.assignment.a2.merged.views.OrderAppFrame;

public class MainDriver implements RunDriver {

    private String[] args;

    public MainDriver(String[] args) {
        this.args = args;
    }

    public void start() {

        if(args.length != 2 && !args[0].equals("-app")){
            System.exit(0);
        }

        switch (args[1]){
            case "ltis":
                java.awt.EventQueue.invokeLater(() ->new InventoryManagerFrame().setVisible(true));
                break;
            case "eis":
                java.awt.EventQueue.invokeLater(() -> new InventoryMainFrame().setVisible(true));
                break;
            case "eos":
                java.awt.EventQueue.invokeLater(() -> new OrderMainFrame().setVisible(true));
                break;
            case "ess":
                java.awt.EventQueue.invokeLater(() -> new ShippingMainFrame().setVisible(true));
                break;

            case "merged":
                java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
                break;
            default:
                System.exit(-1);
                break;
        }

    }
}
