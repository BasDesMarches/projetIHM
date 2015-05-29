package event_handler;

import application.Map;
import application.Worm;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressedEvent implements EventHandler<KeyEvent>{
//public class KeyPressedEvent implements EventHandler<MouseEvent>{
	Worm w;
	Map m;
	
	public KeyPressedEvent(Worm w) {
		super();
		this.w = w;
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

		default:
			break;
		}
		event.consume();
	}
}
