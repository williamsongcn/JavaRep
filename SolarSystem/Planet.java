public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double x,double y,double vx,double vy,double m,String img){
		xxPos=x;
		yyPos=y;
		xxVel=vx;
		yyVel=vy;
		mass=m;
		imgFileName=img;

	}
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;

	}
	public double calcDistance(Planet anotherP){
		double dx = xxPos - anotherP.xxPos;
		double dy = yyPos - anotherP.yyPos;
		double r = Math.sqrt(dx*dx+dy*dy);
		return r;

	}
	public double calcForceExertedBy(Planet anotherP){
		double r = this.calcDistance(anotherP);
		if (r ==0){
			return 0;
		}
		double force = 6.67*Math.pow(10.0,-11)*mass*anotherP.mass/r/r;
		return force;

	}
	public double calcForceExertedByX(Planet anotherP){
		double dx = anotherP.xxPos - xxPos;
		double r = this.calcDistance(anotherP);
		if (r==0){
			return 0;
		}
		double force = this.calcForceExertedBy(anotherP);
		double fx = force*dx/r;
		return fx;
	}
	public double calcForceExertedByY(Planet anotherP){
		double dy = anotherP.yyPos - yyPos;
		double r = this.calcDistance(anotherP);
		if (r==0){
			return 0;
		}
		double force = this.calcForceExertedBy(anotherP);
		double fy = force*dy/r;
		return fy;
	}
	public double calcNetForceExertedByX(Planet[] otherPs){
		double fx = 0;
		for (Planet pn : otherPs){
			fx += this.calcForceExertedByX(pn);
		}
		return fx;
	}
	public double calcNetForceExertedByY(Planet[] otherPs){
		double fy = 0;
		for (Planet pn : otherPs){
			fy += this.calcForceExertedByY(pn);
		}
		return fy;

	}
	public void update(double t,double fx, double fy){
		double ax = fx/mass;
		double ay = fy/mass;
		xxVel = xxVel + ax*t;
		yyVel = yyVel + ay*t;
		xxPos += xxVel*t;
		yyPos += yyVel*t;
	}
	public void draw(){
		String imgPath = "images/"+imgFileName;
		
		StdDraw.picture(xxPos,yyPos,imgPath);
		

	}
}