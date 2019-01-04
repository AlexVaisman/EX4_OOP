package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Algo.AutoPlay;
import Algo.Segment;
import Coords.Convert_pixel_gps;
import GIS.Box;
import GIS.Fruit;
import GIS.Game;
import GIS.Ghost;
import GIS.Pacman;
import GIS.Player;
import Geom.Pixel;
import Geom.Point3D;
import Robot.Play;

/**
 * This class is the main class of the program. it will create the gui with all
 * the option written in the slider menu on the top left. depending on what the
 * user chooses in the gui the class will start a function .
 * 
 * @author Alex vaisman, Shay naor
 *
 */
public class MyFrame extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private BufferedImage myImage;
	private Game game;
	private boolean isGameLoaded;
	private boolean isPlayer;
	private Play play1;

	public MyFrame() {

		/* INIT myImge filed */
		try {
			this.myImage = ImageIO.read(new File("C:\\Users\\A Beast\\Desktop\\Ex4_OOP\\data\\Ariel1.png"));
		} catch (IOException e) {
			System.err.println("ERROR: incorrect path for picture!");
			e.printStackTrace();
		}
		this.setTitle("Pacman");
		isGameLoaded = false;
		this.addMouseListener(this);
		initGUI();
	}

	/**
	 * This function creates all the menu bars and options in the gui. It also has
	 * in it functions which listen to which option was selected. If and option was
	 * selected the corresponding function will start.
	 */
	private void initGUI() {
		/* Create the menu bar. */
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem loadFile = new MenuItem("Load");
		MenuItem saveFile = new MenuItem("Save to CSV");
		MenuItem clearFile = new MenuItem("Clear");
		MenuItem exitFile = new MenuItem("Exit");

		fileMenu.add(loadFile);
		fileMenu.add(saveFile);
		fileMenu.add(clearFile);
		fileMenu.add(exitFile);

		menuBar.add(fileMenu);

		Menu inputMenu = new Menu("Input");
		MenuItem playerInput = new MenuItem("Player");

		inputMenu.add(playerInput);

		menuBar.add(inputMenu);

		Menu playMenu = new Menu("Play");
		MenuItem startPlay = new MenuItem("Start");
		MenuItem startSim = new MenuItem("Start Auto");

		playMenu.add(startPlay);
		playMenu.add(startSim);

		menuBar.add(playMenu);

		setMenuBar(menuBar);

		/* End to create the menu bar. */

		/*
		 * Add action to load File button
		 * https://stackoverflow.com/questions/15703214/save-file-open-file-dialog-box-
		 * using-swing-netbeans-gui-editor
		 */
		loadFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				ChooseButtonLoadFile();
			}
		});

		/*
		 * Add action to save File button
		 * https://stackoverflow.com/questions/15703214/save-file-open-file-dialog-box-
		 * using-swing-netbeans-gui-editor
		 */
		saveFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// ChooseButtonSaveFile(arg0);
			}
		});

		/* Add action to clear File button */
		clearFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Game game = getGame();
				game.getFruits().clear();
				game.getPacmans().clear();
				repaint();
			}
		});

		/* Add action to exit File button */
		exitFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		/* Add action to player input button */
		playerInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				isPlayer = true;
			}
		});

		/* Add action to start play button */
		startPlay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ChooseButtonStartPlay();
			}
		});
		
		/* Add action to startSim play auto button */
		startSim.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ChooseButtonStartSim();
			}
		});

	}

	private void ChooseButtonStartSim() {
		 //AutoPlay start= new	AutoPlay(this.game,this.play1);
		
	}
	
	/**
	 * This function loads a csv file and creates a new game.
	 */
	private void ChooseButtonLoadFile() {
		/* Open load file chooser */
		JFileChooser openFile = new JFileChooser();
		int returnValue = openFile.showOpenDialog(null);

		/* If the file selected */
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = openFile.getSelectedFile();// the file that selected.
			String filePath = selectedFile.getPath();// the path to the file.

			/* If the file that selected is csv file */
			if (filePath.contains(".csv")) {

				/* Load the new game to the game board */
				play1 = new Play(filePath);
				play1.setIDs(206125767, 317524445);
				String map_data = play1.getBoundingBox();
				ArrayList<String> board_data = play1.getBoard();
				this.game = new Game(board_data, map_data);
			   
				play1.setInitLocation(32.1040, 35.2061);
				play1.start();
			
				//System.out.println(play1.getStatistics());
				isGameLoaded = true;
				repaint();
			}
		}

	}

	private void ChooseButtonStartPlay() {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
			
				while (play1.isRuning()) {
				

					ArrayList<String> board_data = play1.getBoard();
					game.updateTheGame(board_data);
					//System.out.println(play1.getStatistics());
					//repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t1.start();
	}

//	/**
//	 * This function saves a game into a csv file.
//	 * @param e , the ActionEven that calls this function.
//	 */
//	private void ChooseButtonSaveFile(ActionEvent e) {
//
//		/* Open save file chooser */
//		JFileChooser chooser = new JFileChooser();
//		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		int result = chooser.showSaveDialog(this);
//
//		/* If the file path selected. */
//		if (result == chooser.APPROVE_OPTION) {
//			File f = chooser.getSelectedFile();
//			String filePath = f.getAbsolutePath();
//			/* Check if the file name end with ".csv" */
//			if (!filePath.endsWith(".csv")) {
//				f = new File(filePath + ".csv");
//				Game2CSV creatGameCSV = new Game2CSV(this.game, f);
//			} else {
//				Game2CSV creatGameCSV = new Game2CSV(this.game, f);
//			}
//		}
//	}

	/**
	 * This function will pain the fruits and pacmans on the map. Depending on if
	 * the gui running a simulation or not.
	 */
	public void paint(Graphics g) {
		
		g.drawImage(this.myImage, 0, 50 , this.getWidth()-7,this.getHeight()-58, this);
		
		if (isGameLoaded) {
			this.game.getMap().setHeight(this.getHeight());
			this.game.getMap().setWidth(this.getWidth());
			/* Draw pacmans */
			Iterator<Pacman> pacIt = this.game.getPacmans().iterator();
			Convert_pixel_gps convert = new Convert_pixel_gps(this.game.getMap());

			while (pacIt.hasNext()) {
				Pacman pac = pacIt.next();
				Pixel pixel = new Pixel(0, 0);
				pixel = convert.convertGPStoPixel(pac.getGps());
				int r = pac.getPicSize();
				int x = pixel.getX() - (r / 2);
				int y = pixel.getY() - (r / 2);
				g.setColor(pac.getColor());
				g.fillOval(x, y , r, r);

			}

			/* Draw fruits */
			Iterator<Fruit> fruitIt = this.game.getFruits().iterator();
			while (fruitIt.hasNext()) {
				Fruit fruit = fruitIt.next();
				Pixel pixel = new Pixel(0, 0);
				pixel = convert.convertGPStoPixel(fruit.getGps());
				int r = fruit.getPicSize();
				int x = pixel.getX() - (r / 2);
				int y = pixel.getY() - (r / 2);
				g.setColor(fruit.getColor());
				g.fillOval(x, y , r, r);
			}

			/* Draw ghosts */
			Iterator<Ghost> ghostIt = this.game.getGhosts().iterator();
			while (ghostIt.hasNext()) {
				Ghost ghost = ghostIt.next();
				Pixel pixel = new Pixel(0, 0);
				pixel = convert.convertGPStoPixel(ghost.getGps());
				int r = ghost.getPicSize();
				int x = pixel.getX() - (r/2);
				int y = pixel.getY() - (r/2);
				g.setColor(ghost.getColor());
				g.fillOval(x, y , r, r);
			}

			/* Draw boxes */
			Iterator<Box> boxIt = this.game.getBoxes().iterator();

			while (boxIt.hasNext()) {
				Box box = boxIt.next();
				Pixel pixelBotLeft = convert.convertGPStoPixel(box.getBotLeft());
				Pixel pixelTopRight = convert.convertGPStoPixel(box.getTopRight());

				int width = pixelTopRight.getX() - pixelBotLeft.getX();
				int height = pixelBotLeft.getY() - pixelTopRight.getY();

				Pixel pixelTopLeft = convert.convertGPStoPixel(box.getTopLeft());

				g.setColor(Color.BLACK);
				g.fillRect(pixelTopLeft.getX(), pixelTopLeft.getY(), width, height);

			}

			/* Draw player */
			Iterator<Player> playerIt = this.game.getPlayers().iterator();

			while (playerIt.hasNext()) {
				Player player = playerIt.next();
				if (convert.isIn(player.getGps())) {
					Pixel pixel = new Pixel(0, 0);
					pixel = convert.convertGPStoPixel(player.getGps());
					int r = player.getPicSize();
					int x = pixel.getX() - (r / 2);
					int y = pixel.getY()- (r / 2);
					g.setColor(player.getColor());
					g.fillOval(x, y , r, r);
				}
			}
		}

	}

	public void mouseClicked(MouseEvent e) {
		/*
		 * if (isPlayer) { int x = e.getX(); int y = e.getY(); Pixel pixel = new
		 * Pixel(x, y); Convert_pixel_gps convert = new
		 * Convert_pixel_gps(this.game.getMap()); Point3D gps = new
		 * Point3D(convert.convertPixeltoGPS(pixel));
		 * this.play1.setInitLocation(gps.x(), gps.y());
		 * 
		 * isPlayer = false; } repaint();
		 */
		
		if(!game.getPlayers().isEmpty()) {
			int x = e.getX();
			int y = e.getY();
			Pixel pixDirection = new Pixel(x,y);
			Convert_pixel_gps convert = new Convert_pixel_gps(this.game.getMap());
			Point3D gpsDirection = new Point3D(convert.convertPixeltoGPS(pixDirection));
			game.getPlayers().get(0).findOrientation(gpsDirection);
			play1.rotate(game.getPlayers().get(0).getOrientation());
			System.out.println(play1.getStatistics());
			repaint();
	
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public BufferedImage getMyImage() {
		return myImage;
	}

	public Game getGame() {
		return this.game;
	}

}
