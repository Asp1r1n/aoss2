package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;

public class TreesController extends InventoryController {
    public TreesController() {
        super();
        super.address += App.TREES_TABLE;
    }
}
