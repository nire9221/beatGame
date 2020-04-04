package beatGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
// notesize 100 x 400 (game info img)
// bar 1280 x60 + bar 2 1280 x4 (game info img) 
// make 2 of them (one for basic, one for judgement line)

//100 x 630 each vertical lines (note route)
// 4x 630 구분  (note route line)
// make 2 of them (one for basic, one for pressed(noteRoutePressed))

//need drum beat sound x 6 

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon((Main.class.getResource("../images/closeEntered.jpg")));
	private ImageIcon exitButtonImage = new ImageIcon((Main.class.getResource("../images/close.jpg")));
	private ImageIcon startButtonEnteredImage = new ImageIcon((Main.class.getResource("../images/startEntered.jpg")));
	private ImageIcon startButtonImage = new ImageIcon((Main.class.getResource("../images/start.jpg")));
	private ImageIcon stopButtonEnteredImage = new ImageIcon((Main.class.getResource("../images/stopEntered.jpg")));
	private ImageIcon stopButtonImage = new ImageIcon((Main.class.getResource("../images/stop.jpg")));
	private ImageIcon leftButtonEnteredImage = new ImageIcon((Main.class.getResource("../images/leftEntered.jpg")));
	private ImageIcon leftButtonImage = new ImageIcon((Main.class.getResource("../images/left.jpg")));
	private ImageIcon rightButtonEnteredImage = new ImageIcon((Main.class.getResource("../images/rightEntered.jpg")));
	private ImageIcon rightButtonImage = new ImageIcon((Main.class.getResource("../images/right.jpg")));
	private ImageIcon easyButtonEnteredImage = new ImageIcon((Main.class.getResource("../images/easySelected.jpg")));
	private ImageIcon easyButtonImage = new ImageIcon((Main.class.getResource("../images/easy.jpg")));
	private ImageIcon hardButtonEnteredImage = new ImageIcon((Main.class.getResource("../images/hardSelected.jpg")));
	private ImageIcon hardButtonImage = new ImageIcon((Main.class.getResource("../images/hard.jpg")));
	private ImageIcon backButtonEnteredImage = new ImageIcon((Main.class.getResource("../images/backEntered.jpg")));
	private ImageIcon backButtonImage = new ImageIcon((Main.class.getResource("../images/back.jpg")));

	
	private Image background = new ImageIcon(Main.class.getResource("../images/background.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.svg")));

	private JButton exitButton = new JButton(exitButtonImage);
	private JButton startButton = new JButton(startButtonImage);
	private JButton stopButton = new JButton(stopButtonImage);
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JButton easyButton = new JButton(easyButtonImage);
	private JButton hardButton = new JButton(hardButtonImage);
	private JButton backButton = new JButton(backButtonImage);

	private int mouseX, mouseY;

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;

	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Music selectedMusic;
	private Image selectedImage;
	private Music introMusic = new Music("music.mp3", true);
	private int nowSelected = 0; // 현재 선택된 트랙

	public static Game game;
	
	public DynamicBeat() {
		
		//String titleImage, String startImage, String gameImage, String startMusic, String gameMusic
				trackList.add(new Track("gamebg2.jpg", "gamebg2.jpg", "background2.jpg", "music2Selected.mp3", "music2.mp3", "music2"));
				trackList.add(new Track("gamebg3.jpg", "gamebg3.jpg", "background3.jpg", "music3Selected.mp3", "music3.mp3", "music3"));
		
		setUndecorated(true); // hide menu bar
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 스크린 사이즈
		setResizable(false); // 한번 만들어진 사이즈는 바뀔수 없음을 선언
		setLocationRelativeTo(null); // 게임창이 스크린 중앙에 오게 선
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 클로즈창을 누르면 게임이 종료되게 선언
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // white background
		setLayout(null);

		addKeyListener(new KeyListner());
		// play music when starting game
		introMusic.start();



//exit button
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
//				buttonEntedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonClickedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000); // exit button will be working after 1 sec clicking the exit button --- can hear
										// the exit button sound;
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);

//start button 
		startButton.setBounds(40, 200, 300, 300);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
//				buttonEntedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//				Music buttonEnteredMusic = new Music("buttonClickedMusic.mp3", false);
//				buttonEnteredMusic.start();
				// game start event
				enterMain();

			}
		});
		add(startButton);

//stop button		
		stopButton.setBounds(40, 330, 300, 300);
		stopButton.setBorderPainted(false);
		stopButton.setContentAreaFilled(false);
		stopButton.setFocusPainted(false);
		stopButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				stopButton.setIcon(stopButtonEnteredImage);
				stopButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
//				buttonEntedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				stopButton.setIcon(stopButtonImage);
				stopButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//				Music buttonEnteredMusic = new Music("buttonClickedMusic.mp3", false);
//				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000); // exit button will be working after 1 sec clicking the exit button --- can hear
										// the exit button sound;
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(stopButton);

//left button		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//					Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
//					buttonEntedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//				Music buttonEnteredMusic = new Music("buttonClickedMusic.mp3", false);
//				buttonEnteredMusic.start();
				// left button event
				selectLeft();
			}
		});
		add(leftButton);

//right button		
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
//				buttonEntedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//				Music buttonEnteredMusic = new Music("buttonClickedMusic.mp3", false);
//				buttonEnteredMusic.start();
				// right button event
				selectRight();
			}
		});
		add(rightButton);

//easy button		
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 67, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//						Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
//						buttonEntedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//						Music buttonEnteredMusic = new Music("buttonClickedMusic.mp3", false);
//						buttonEnteredMusic.start();
				// easy mode
				gameStart(nowSelected, "Easy");

			}
		});
		add(easyButton);

//hard button		
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 67, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//						Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
//						buttonEntedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//						Music buttonEnteredMusic = new Music("buttonClickedMusic.mp3", false);
//						buttonEnteredMusic.start();
				// hard mode
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

//hard button		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 67, 67);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//						Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
//						buttonEntedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
//						Music buttonEnteredMusic = new Music("buttonClickedMusic.mp3", false);
//						buttonEnteredMusic.start();
				// back to main event
				backMain();

			}
		});
		add(backButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}

		if (isGameScreen) {
			game.screenDraw(g);

		}
		paintComponents(g); // always existing image (not dynamic, ex: menubar, button, add(....))
		
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
			titleImage = new ImageIcon(
					Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
			selectedImage = new ImageIcon(
					Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
			selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
			selectedMusic.start();
		}
	}

	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = trackList.size() - 1;
		} else {
			nowSelected--;
			selectTrack(nowSelected);
		}
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) {
			nowSelected = 0;
		} else {
			nowSelected++;
			selectTrack(nowSelected);
		}
	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null) {
			selectedMusic.close();
			isMainScreen = false;
			leftButton.setVisible(false);
			rightButton.setVisible(false);
			easyButton.setVisible(false);
			hardButton.setVisible(false);
			background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
					.getImage();
			backButton.setVisible(true);
			isGameScreen = true;
			game = new Game (trackList.get(nowSelected).getTitleName(),difficulty, trackList.get(nowSelected).getGameMusic());
			game.start();
			setFocusable(true);
		}
	}

	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/background.jpg")).getImage();
		backButton.setVisible(false);
		isGameScreen = false;
		selectTrack(nowSelected);
		game.close();
	}

	public void enterMain() {
		startButton.setVisible(false);
		stopButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/gamebg.jpg")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}
}
