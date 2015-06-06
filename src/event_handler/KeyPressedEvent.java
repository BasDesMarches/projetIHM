package event_handler;

import views.World;
import application.Map;
import application.Worm;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressedEvent implements EventHandler<KeyEvent>{
	Map m;
	World wo;
	
	public KeyPressedEvent(World wo) {
		super();
		this.wo=wo;
		
	}

	@Override
	public void handle(KeyEvent event) {
		Worm w = wo.getCurrentWorm().getWorm();
		switch (event.getCode()) {
		case D:
		case RIGHT:
			w.moveRight();
			break;
			
		case Q:
		case LEFT:
			w.moveLeft();
			break;

		case ENTER:
			wo.nextWorm();
			break;
		default:
			break;
		}
		event.consume();
	}
}
