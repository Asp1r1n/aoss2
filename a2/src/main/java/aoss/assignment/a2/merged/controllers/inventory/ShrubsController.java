package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.models.UserSession;

public class ShrubsController extends InventoryController {
    public ShrubsController(UserSession session) {
        super(session);
        super.address += App.SHRUBS_TABLE;
    }
}
