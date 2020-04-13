package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;

public class ShrubsController extends InventoryController {
    public ShrubsController() {
        super();
        super.address += App.SHRUBS_TABLE;
    }
}
