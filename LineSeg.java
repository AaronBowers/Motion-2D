import java.awt.*;
import java.lang.*;

public class LineSeg
{

	//FIELDS

	//Coodinates
	private double x1;
	private double y1;
	private double x2;
	private double y2;

	private int r;

	//vector
	private double dx1;
	private double dy1;
	private double dx2;
	private double dy2;


	//angle
	private double rad;


	private double speed;//magnatude must be given

	//Force
	private double acceleration;//magnatude must be given
	private double averageSpeed;
	private double distance;
	private double force;

	//calculating speed
	private double velocity, thetaVelocity;
	private double time;

	//calculating force
	private double mass;
	private double power;
	private double energy;




	private int type;
	private Color color1;
	private boolean ready;

	//constructorsdsa
	public LineSeg(int type)
	{
		this.type = type;

		//default Particle atributes
		if (type == 1)
		{
			color1 = Color.RED;
		}
	}


	//fucntions
	public double getx1() {return x1; };
	public double gety1() {return y1; };

	public double getx2() {return x2; };
	public double gety2() {return y2; };

	public double getdx1() {return dx1; };
	public double getdy1() {return dy1; };

	public double getdx2() {return dx2; };
	public double getdy2() {return dy2; };

	public double setx1(double x1) {x1 = this.x1; return x1;};
	public double sety1(double y1) {y1 = this.y1; return y1;};

	public double setdx1(double dx1) {this.dx1 = dx1; return dx1;};
	public double setdy1(double dy1) {this.dy1 = dy1; return dy1;};

	public void NsUpdate()
	{

	}

	public void draw(Graphics2D g)
	{
		g.setColor(color1);
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		g.setStroke(new BasicStroke(3));

	}

}
