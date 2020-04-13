package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;

public class GenomicsController extends InventoryController {
    public GenomicsController() {
        super();
        super.address += App.GENOMICS_TABLE;
    }
}
