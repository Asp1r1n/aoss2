package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;

public class CultureboxesController extends InventoryController {
    public CultureboxesController() {
        super();
        super.address += App.CULTUREBOXES_TABLE;
    }
}
