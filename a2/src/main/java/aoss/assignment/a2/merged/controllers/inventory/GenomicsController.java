package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.models.UserSession;

public class GenomicsController extends InventoryController {
    public GenomicsController(UserSession session) {
        super(session);
        super.address += App.GENOMICS_TABLE;
    }
}
