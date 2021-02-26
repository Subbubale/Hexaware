package Utilities;

import org.openqa.selenium.Keys;

public  class Press {

	public static Keys Key(String key) {
		key = key.toUpperCase();

		switch (key) {
		case "ALT":

			return Keys.ALT;

		case "ADD":
			return Keys.ADD;

		case "ARROW_DOWN":
			return Keys.ARROW_DOWN;

		case "ARROW_LEFT":
			return Keys.ARROW_LEFT;

		case "ARROW_RIGHT":
			return Keys.ARROW_RIGHT;

		case "ARROW_UP":
			return Keys.ARROW_UP;

		case "BACK_SPACE":
			return Keys.BACK_SPACE;

		case "CANCEL":
			return Keys.CANCEL;

		case "CLEAR":
			return Keys.CLEAR;

		case "COMMAND":
			return Keys.COMMAND;

		case "CONTROL":
			return Keys.CONTROL;

		case "DECIMAL":
			return Keys.DECIMAL;

		case "DELETE":
			return Keys.DELETE;

		case "DIVIDE":
			return Keys.DIVIDE;

		case "DOWN":
			return Keys.DOWN;

		case "END":
			return Keys.END;

		case "ENTER":
			return Keys.ENTER;

		case "EQUALS":
			return Keys.EQUALS;

		case "ESCAPE":
			return Keys.ESCAPE;

		case "F1":
			return Keys.F1;

		case "F10":
			return Keys.F10;

		case "F11":
			return Keys.F11;

		case "F12":
			return Keys.F12;

		case "F2":
			return Keys.F2;

		case "F3":
			return Keys.F3;

		case "F4":
			return Keys.F4;

		case "F5":
			return Keys.F5;

		case "F6":
			return Keys.F6;

		case "F7":
			return Keys.F7;

		case "F8":
			return Keys.F8;

		case "F9":
			return Keys.F9;

		case "HELP":
			return Keys.HELP;

		case "HOME":
			return Keys.HOME;

		case "INSERT":
			return Keys.INSERT;

		case "LEFT":
			return Keys.LEFT;

		case "LEFT_ALT":
			return Keys.LEFT_ALT;

		case "LEFT_CONTROL":
			return Keys.LEFT_CONTROL;

		case "LEFT_SHIFT":
			return Keys.LEFT_SHIFT;

		case "META":
			return Keys.META;

		case "MULTIPLY":
			return Keys.MULTIPLY;

		case "NUMPAD0":
			return Keys.NUMPAD0;

		case "NUMPAD1":
			return Keys.NUMPAD1;

		case "NUMPAD2":
			return Keys.NUMPAD2;

		case "NUMPAD3":
			return Keys.NUMPAD3;

		case "NUMPAD4":
			return Keys.NUMPAD4;

		case "NUMPAD5":
			return Keys.NUMPAD5;

		case "NUMPAD6":
			return Keys.NUMPAD6;

		case "NUMPAD7":
			return Keys.NUMPAD7;

		case "NUMPAD8":
			return Keys.NUMPAD8;

		case "NUMPAD9":
			return Keys.NUMPAD9;

		case "PAGE_DOWN":
			return Keys.PAGE_DOWN;

		case "PAGE_UP":
			return Keys.PAGE_UP;

		case "PAUSE":
			return Keys.PAUSE;

		case "RETURN":
			return Keys.RETURN;

		case "RIGHT":
			return Keys.RIGHT;

		case "SEMICOLON":
			return Keys.SEMICOLON;

		case "SEPARATOR":
			return Keys.SEPARATOR;

		case "SHIFT":
			return Keys.SHIFT;

		case "SPACE":
			return Keys.SPACE;

		case "SUBTRACT":
			return Keys.SUBTRACT;

		case "TAB":
			return Keys.TAB;

		case "UP":
			return Keys.UP;

		default:
			return Keys.NULL;
		}
	}
}
