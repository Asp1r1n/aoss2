package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.models.UserSession;

public class SeedsController extends InventoryController {
    public SeedsController(UserSession session) {
        super(session);
        super.address += App.SEEDS_TABLE;
    }
}
