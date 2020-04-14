package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.models.UserSession;

public class CultureboxesController extends InventoryController {
    public CultureboxesController(UserSession session) {
        super(session);
        super.address += App.CULTUREBOXES_TABLE;
    }
}
