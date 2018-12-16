public class NBody{
	public static int readNum(String path){
		In in = new In(path);
		int number = in.readInt();
		return number;

	}
	public static double readRadius(String path){
		In in = new In(path);
		int number = in.readInt();
		double r = in.readDouble();
		return r;
	}
	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[num];
		for (int i = 0 ; i < num ; i++){
			double xxPos=in.readDouble();
			double yyPos=in.readDouble();
			double xxVel=in.readDouble();
			double yyVel=in.readDouble();
			double mass=in.readDouble();
			String imgFileName=in.readString();
			planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}
		return planets;

	}
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		int num = readNum(filename);
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		for (double t = 0 ; t < T ;t+=dt){
			double[] xForces = new double[num];
			double[] yForces = new double[num];
			for (int i=0;i<num;i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			
			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-radius,radius);
			StdDraw.clear();
			StdDraw.picture(0,0,"./images/starfield.jpg");
			
			for (Planet pn : planets){
				pn.draw();

			}
			StdDraw.show();
			StdDraw.pause(10);
		}
	}
}