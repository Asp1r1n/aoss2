package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;

public class ReferencematerialsController extends InventoryController {
    public ReferencematerialsController() {
        super();
        super.address += App.REFERENCEMATERIALS_TABLE;
    }
}
