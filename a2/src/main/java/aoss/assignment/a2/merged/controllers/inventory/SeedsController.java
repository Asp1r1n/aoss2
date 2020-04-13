package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;

public class SeedsController extends InventoryController {
    public SeedsController() {
        super();
        super.address += App.SEEDS_TABLE;
    }
}
