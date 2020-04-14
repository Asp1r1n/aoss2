package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.models.UserSession;

public class ReferencematerialsController extends InventoryController {
    public ReferencematerialsController(UserSession session) {
        super(session);
        super.address += App.REFERENCEMATERIALS_TABLE;
    }
}
