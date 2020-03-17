package beatGame;

public class Main {	
	//모든프로젝트안에서 공유할수 있는 변수, final은 한번 선언되면 절대 바뀌지 않음 
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	public static void main(String[] args) {
		
		new DynamicBeat();
	}

}
