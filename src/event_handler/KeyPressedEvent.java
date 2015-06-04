package event_handler;

import application.Map;
import application.World;
import application.Worm;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressedEvent implements EventHandler<KeyEvent>{
//public class KeyPressedEvent implements EventHandler<MouseEvent>{
	Worm w;
	Map m;
	World wo;
	
	public KeyPressedEvent(Worm w, World wo) {
		super();
		this.w = w;
		this.wo=wo;
		
	}

	@Override
	public void handle(KeyEvent event) {
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
		default:
			break;
		}
		event.consume();
	}
}
