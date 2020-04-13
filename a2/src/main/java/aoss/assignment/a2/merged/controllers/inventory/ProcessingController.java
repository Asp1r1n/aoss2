package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;

public class ProcessingController extends InventoryController {
    public ProcessingController() {
        super();
        super.address += App.PROCESSING_TABLE;
    }
}
