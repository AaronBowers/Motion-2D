import java.awt.*;
import java.lang.*;

public class Particle {

	//FIELDS

	//Coodinates
	private double x;
	private double y;

	private int r;

	//vector
	private double dx;
	private double dy;


	//angle
	private double rad;
	private double angle;

	private double speed;//magnatude must be given

	//Force
	private double acceleration;
	private double force;

	//calculating speed
	private double velocity, thetaVelocity;
	private double time;

	//calculating force
	private double mass;
	private double power;
	private double energy;

	//observation checks
	private int type;
	private Color color1;
	private boolean ready;

	//constructors
	public Particle(int type)//add speed, add angle, add mass, add spin
	{
		this.type = type;

		//default Particle atributes
		if (type == 1)
		{
			color1 = Color.BLUE;
			speed = 5;//Min + (int)(Math.random() * ((Max - Min) + 1));
			r = 10;//Min + (int)(Math.random() * ((Max - Min) + 1));
		}

		//generates starting coodinates
		x = (Math.random() * Nstage.WIDTH / 2 + Nstage.WIDTH / 4);
		y = (Math.random() * Nstage.WIDTH / 2 + Nstage.WIDTH / 4);

		double angle = Math.random() * 140 + 20;
		rad = Math.toRadians(angle);

		//this is what? acceleration??
		dx = Math.cos(rad) * speed;
		dy = Math.sin(rad) * speed;
	}


	//fucntions
	public double getx() {return x; };
	public double gety() {return y; };
	public double getr() {return r; };
	public double getdx() {return dx; };
	public double getdy() {return dy; };
	public double getrad() {return rad;};

	private double getspeed() {return speed;};

	public double setx(double x) {x = this.x; return x;};
	public double sety(double y) {y = this.y; return y;};

	private double setspeed(double speed) {speed = this.speed; return speed;};

	public double setdx(double dx) {this.dx = dx; return dx;};
	public double setdy(double dy) {this.dy = dy; return dy;};

	public double setrad(double rad) {this.rad = rad; return rad;};

	public int setr(int r) {this.r = r; return r;};


	//public int setforce

	public void NsUpdate()
	{
		x += dx;
		y += dy;
		//Particle Collison behavior with container
		if (x < r && dx < 0) dx = -dx;
		if (y < r && dy < 0) dy = -dy;

		if (x > Nstage.WIDTH - r && dx >= 0) dx = -dx;
		if (y > Nstage.HEIGHT - r && dy >= 0) dy = -dy;

	}

	public void draw(Graphics2D g)
	{
		g.setColor(color1);
		g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);

		g.setStroke(new BasicStroke(3));
		g.setColor(color1.darker());
		g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
		g.setStroke(new BasicStroke(1));

	}

}
