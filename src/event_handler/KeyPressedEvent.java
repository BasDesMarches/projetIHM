package event_handler;

import application.Map;
import application.Worm;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressedEvent implements EventHandler<KeyEvent>{
//public class KeyPressedEvent implements EventHandler<MouseEvent>{
	Worm w;
	Map m;
	
	public KeyPressedEvent(Worm w, Map m) {
		super();
		this.w = w;
		this.m = m;
	}

	@Override
	public void handle(KeyEvent event) {
		switch (event.getCode()) {
		case D:
		case RIGHT:
			w.moveRight(m);
			break;
			
		case Q:
		case LEFT:
			w.moveLeft(m);
			break;

		default:
			break;
		}
		event.consume();
	}

//	@Override
//	public void handle(MouseEvent event) {
//		System.out.println("Coucou");
//		switch (event.getButton()) {
//		case PRIMARY:
//			System.out.println("Coucou1");
//			w.getWorm().moveRight(m);
//			System.out.println(w.xProperty());
//			System.out.println(w.yProperty());
//			AnchorPane.setTopAnchor(worm, w.yProperty().multiply(5).doubleValue());
//			AnchorPane.setLeftAnchor(worm, w.xProperty().multiply(5).doubleValue());
//			break;
//			
//		case SECONDARY:
//			System.out.println("Coucou2");
//			w.getWorm().moveLeft(m);
//			System.out.println(w.xProperty());
//			System.out.println(w.yProperty());
//			AnchorPane.setTopAnchor(worm, w.yProperty().multiply(5).doubleValue());
//			AnchorPane.setLeftAnchor(worm, w.xProperty().multiply(5).doubleValue());
//			break;
//
//		default:
//			break;
//		}
//		event.consume();
//	}

}
