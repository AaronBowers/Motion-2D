import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;

import java.lang.*;

public class Nstage extends JPanel implements Runnable, KeyListener {
	//FIELDS
	public static int WIDTH = 600;
	public static int HEIGHT = 600;

	private Thread thread;
	private boolean running;

	private BufferedImage image;
	private Graphics2D g;

	private static int ENGFPS = 30;
	private double averageFps;

	public static ArrayList<Particle> particles;


	//CONSTUCTOR
	public Nstage(){
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();

	}

	//FUNCTIONS
	public void addNotify(){
		super.addNotify();
		if (thread == null){
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
	}

	public void run()
	{
		running = true;

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		//particle = new Particle();

		particles = new ArrayList<Particle>();

		//Object generator
		for(int i = 0; i < 10; i++)
		{
			particles.add(new Particle(1));
		}


		long startTime;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;

		int frameCount = 0;
		//int maxFrameCount = ENGFPS;

		long targetTime = 1000/ENGFPS;

		//engine loop
		while (running){
			startTime = System.nanoTime();

			NsUpdate();
			NsRender();
			NsDraw();

			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;

			try{
				Thread.sleep(waitTime);
			}
			catch(Exception e){

			}

			totalTime += System.nanoTime() - startTime;
			frameCount++;

			if (frameCount == ENGFPS){
				averageFps = 1000.0 / ((totalTime / frameCount)/1000000.0);
				frameCount = 0;
				totalTime = 0;
			}

		}
	}

	private void NsUpdate()
	{

		for(int i = 0; i < particles.size(); i++){
			particles.get(i).NsUpdate();
		}
		//detect
		//check array for particle
		//check first particle vs next particle
		//do particles inhabit same space
		//if yes rebound

		//itterate through list of particles
		for (int i = 0; i < particles.size(); ++i)
		{
					for (int j = 0; j < particles.size(); ++j)
					{
							if (i < j)
							{

								Particle p1 = particles.get(i);
								double p1x = p1.getx();
								double p1y = p1.gety();
								double p1r = p1.getr();
								double p1dx = p1.getdx();
								double p1dy = p1.getdy();
								double p1rad = p1.getrad();
								//p1.setr(15);

								//manula check of second element
								Particle p2 = particles.get(j);
								double p2x = p2.getx();
								double p2y = p2.gety();
								double p2r = p2.getr();
								double p2dx = p2.getdx();
								double p2dy = p2.getdy();
								double p2rad = p2.getrad();
								//interection issues


								//attmept at each ball haivng its own gravity
							/*	double r = (Math.hypot(p1x - p2x, p1y - p2y) + (p1r + p2r));

								p1dx = ((p2x - p1x) /r);
								p1dy = ((p2y - p1y) / r);

								//p2dx = (p1x - p2x);
								//p2dy = (p1y - p2y);

								p1.setdx(p1dx);
								p1.setdy(p1dy);
								p2.setdx(p2dx);
								p2.setdy(p2dy);
*/
								//end interaction issues

								if (Math.hypot(p1x - p2x, p1y - p2y) <= p1r + p2r)
								{
									System.out.println("colision");
									p1.setdx((p1dx * (p1r - p2r) + (2 * p2r * p2dx)) / (p1r + p2r));
									p1.setdy((p1dy * (p1r - p2r) + (2 * p2r * p2dy)) / (p1r + p2r));
									p2.setdx((p2dx * (p2r - p1r) + (2 * p1r * p1dx)) / (p1r + p2r));
									p2.setdy((p2dy * (p2r - p1r) + (2 * p1r * p1dy)) / (p1r + p2r));
								}


								//gravitaion
								//double Fx = 0.0;
								//double Fy = 0.0;
								//double F = 0.0;

								//F = (p2x - p1x);
								//F = (p2y - p1y);
								//Fx = F;
								//Fy = F;



								//Math.hypot(p1x - p2x, p1y - p2y


							}
					}
		}
/*
		//manual check of first element
		Particle p1 = particles.get(0);
		double p1x = p1.getx();
		double p1y = p1.gety();
		double p1r = p1.getr();
		double p1dx = p1.getdx();
		double p1dy = p1.getdy();
		double p1rad = p1.getrad();
		//p1.setr(15);

		//manula check of second element
		Particle p2 = particles.get(1);
		double p2x = p2.getx();
		double p2y = p2.gety();
		double p2r = p2.getr();
		double p2dx = p2.getdx();
		double p2dy = p2.getdy();
		double p2rad = p2.getrad();

		//check px and py and adjust px and dx using laws of conservation and
		//engergy
		//if (p1x < p1r && dx < 0) dx = -dx;
		//if (y < r && dy < 0) dy = -dy;

		//this a check to make sure that if anthing is beyound the screen its brought back
		//if (x > Nstage.WIDTH - r && dx >= 0) dx = -dx;
		//if (y > Nstage.HEIGHT - r && dy >= 0) dy = -dy;

			if (Math.hypot(p1x - p2x, p1y - p2y) <= p1r + p2r)
			{
				System.out.println("colision");

				//axial needs calculated
				//current rotations


				p1.setdx((p1dx * (p1r - p2r) + (2 * p2r * p2dx)) / (p1r + p2r));
				p1.setdy((p1dy * (p1r - p2r) + (2 * p2r * p2dy)) / (p1r + p2r));

				//p1rad = p1.getrad();

				//new radius
				//p1.setrad();
				//set new angle and new direction
				//p1.setdx(-p1dx);
				//p1.setdy(-p1dy);

				//compute  new angle for p2
				//p2.setrad(p2rad);

				//set new angle and new direction
				//p2.setdx(-p2dx);
				//p2.setdy(-p2dy);

				//axial needs calculated

				p2.setdx((p2dx * (p2r - p1r) + (2 * p1r * p1dx)) / (p1r + p2r));
				p2.setdy((p2dy * (p2r - p1r) + (2 * p1r * p1dy)) / (p1r + p2r));



				//if (x > - r && dx >= 0) dx = -dx;
				//if (y > Nstage.HEIGHT - r && dy >= 0) dy = -dy;

			}

*/
		//use ittertor to cross check durrent coodinates of entity

	}

	private void NsRender(){
		g.setColor(new Color(10, 100, 150));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.drawString("FPS " + averageFps , 10, 10);

		g.drawString("particle count: " + particles.size(), 10, 20);

		for(int i = 0; i < particles.size(); i++)
		{
			Particle p = particles.get(i);
			double px = p.getx();
			double py = p.gety();
			double pr = p.getr();

			//this text moves lol
			//g.drawString("p" + i + particles.get(i) ,  60 +(int) px, -10 +(int) py);
			//g.drawString("x: " + (int) px ,  60 +(int) px, 0 +(int) py);
			//g.drawString("y: " + (int) py , 60 +(int) px, 10 +(int) py);

			for (int j = 0; j < particles.size(); ++j)
			{
					if (i < j)
					{

						Particle p1 = particles.get(i);
						double p1x = p1.getx();
						double p1y = p1.gety();
						double p1r = p1.getr();
						double p1dx = p1.getdx();
						double p1dy = p1.getdy();
						double p1rad = p1.getrad();

						//manula check of second element
						Particle p2 = particles.get(j);
						double p2x = p2.getx();
						double p2y = p2.gety();
						double p2r = p2.getr();
						double p2dx = p2.getdx();
						double p2dy = p2.getdy();
						double p2rad = p2.getrad();

						g.setColor(new Color(3, 70, 120));
						g.drawLine((int)p1x,(int)p1y,(int)p2x,(int)p2y);
					}
			}

		}


		//draw particle
		for(int i = 0; i < particles.size(); i++){
			particles.get(i).draw(g);
		}

	}

	private void NsDraw(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	public void keyTyped(KeyEvent key){

	}

	public void keyPressed(KeyEvent key){

	}

	public void keyReleased(KeyEvent key){

	}
}
