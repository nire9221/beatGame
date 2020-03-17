package beatGame;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{
	
	private Player player; // from library 
	private boolean isLoop; //현재 재생중인 곡이 무한반복인지?
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File (Main.class.getResource("../music/" + name).toURI()); //open file
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis); 
			player = new Player(bis);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public int getTime() {
		if (player ==null) {
			return 0;
		} return player.getPosition();
	}
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); //stop music 
	}
	
	@Override 
	public void run() { // when inheritance of thread, it has to be used  
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); 
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
