package aoss.assignment.a2.merged.controllers.inventory;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.models.UserSession;

public class ProcessingController extends InventoryController {
    public ProcessingController(UserSession session) {
        super(session);
        super.address += App.PROCESSING_TABLE;
    }
}
