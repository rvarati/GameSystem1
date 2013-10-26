package gamePlayEngine.model.gameElement;

import javax.xml.bind.annotation.XmlElement;

import gamePlayEngine.model.audio.Audio;
import gamePlayEngine.model.gameElement.graphic.Props;

public class DeclarationsInitializations {

	private Props props;
	private Audio audio;
	
	@XmlElement(name="Props")
	public Props getProps() {
		return props;
	}
	public void setProps(Props props) {
		this.props = props;
	}
	@XmlElement(name="Audio")
	public Audio getAudio() {
		return audio;
	}
	public void setAudio(Audio audio) {
		this.audio = audio;
	}
	
	
	
}
