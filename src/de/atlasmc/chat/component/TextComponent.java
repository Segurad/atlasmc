package de.atlasmc.chat.component;

public class TextComponent extends AbstractComponent {

	private String text;
	
	@Override
	public String getLegacyText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJsonText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(ChatComponent text) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
