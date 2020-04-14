package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.models.UserSession;

public class TreesController extends InventoryController {
    public TreesController(UserSession session) {
        super(session);
        super.address += App.TREES_TABLE;
    }
}
