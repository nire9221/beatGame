package beatGame;

public class Track {
	
	private String titleImage; // 제목부분 이미
	private String StartImage; // 게임선택 창 표지 이미
	private String gameImage; //해당곡을 실행했을때 표지 이미
	private String startMusic; //게임선택 창 음악
	private String gameMusic; //해당 곡을 실행했을때 음악
	private String titleName; // title
	
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return StartImage;
	}
	public void setStartImage(String startImage) {
		StartImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic, String titleName) {
		super();
		this.titleImage = titleImage;
		StartImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}
	
	
}
