package gameutil.geom.g2D;

import java.awt.Rectangle;
import java.util.ArrayList;

import gameutil.geom.Tuple;
import gameutil.geom.Vector;



public class VectorR2 extends LineSegR2 {
	double mX;
	double mY;
	
	public VectorR2(PointR2 base, PointR2 end) throws Exception {
		super(base.getX(), base.getY(), end.getX(), end.getY());
		mX=end.getX()-base.getX();
		mY=end.getY()-base.getY();
	}
	
	public VectorR2(Vector v) throws Exception {
		super(0, 0, v.getSpds().i(0), v.getSpds().i(1));
		mX=v.getSpds().i(0);
		mY=v.getSpds().i(1);
	}
	
	public VectorR2(double mX,double mY) throws Exception {
		super(0, 0, mX, mY);
		this.mX=mX;
		this.mY=mY;
	}

	public PointR2 base() {
		return new PointR2(x1, y1);
	}

	public PointR2 end() {
		return new PointR2(x2, y2);
	}

	public PointR2 intersection(Rectangle r) {
		if (intersects(r)) {
			if (r.contains(base().getX(), base().getY()) && r.contains(end().getX(), end().getY())) {
				return base();
			} else if (r.contains(base().getX(), base().getY())) {
				LineSegR2[] segments = LineSegR2.rectToLineSegs(r);
				for (LineSegR2 l : segments) {
					if (intersects(l)) {
						try {
							return intersection(l);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				LineSegR2[] segments = LineSegR2.rectToLineSegs(r);
				ArrayList<PointR2> points = new ArrayList<>();
				for (LineSegR2 l : segments) {
					if (intersects(l)) {
						try {
							points.add(intersection(l));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				PointR2 closest = points.get(0);
				for (PointR2 p : points) {
					if (p.distance(base()) > closest.distance(base())) {
						closest = p;
					}
				}
				return closest;
			}

		}
		return null;
	}
	
	public PointR2 intersection(RectangleR2 r) {
		if (intersects(r)) {
			if (r.contains(base()) && r.contains(end())) {
				return null;
			} else if (r.contains(base())) {
				LineSegR2[] segments = LineSegR2.rectToLineSegs(r);
				for (LineSegR2 l : segments) {
					if (intersects(l)) {
						try {
							return intersection(l);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				LineSegR2[] segments = LineSegR2.rectToLineSegs(r);
				ArrayList<PointR2> points = new ArrayList<>();
				int i=0;
				for (LineSegR2 l : segments) {
					i++;
					
					if (intersects(l)) {
						System.out.println("adding point");
						try {
							points.add(intersection(l));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				System.out.println(i+" segments");
				PointR2 closest = points.get(0);
				for (PointR2 p : points) {
					if (p.distance(base()) > closest.distance(base())) {
						closest = p;
					}
				}
				return closest;
			}

		}
		return null;
	}
	
	public double getMagnetudeX() {
		return mX;
	}
	
	public double getMagnetudeY() {
		return mY;
	}
	
	public VectorR2 $A$(VectorR2 v){
		try {
			return new VectorR2(toVector() .$A$ (v.toVector()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public VectorR2 $A$(double s){
		try {
			return new VectorR2(toVector() .$A$ (s));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public VectorR2 $S$(VectorR2 v){
		try {
			return new VectorR2(toVector() .$S$ (v.toVector()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public VectorR2 $S$(double s){
		try {
			return new VectorR2(toVector() .$S$ (s));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public VectorR2 $X$(VectorR2 v){
		try {
			return new VectorR2(toVector() .$X$ (v.toVector()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public VectorR2 $X$(double s){
		try {
			return new VectorR2(toVector() .$X$ (s));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public VectorR2 $D$(VectorR2 v){
		try {
			return new VectorR2(toVector() .$D$ (v.toVector()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public VectorR2 $D$(double s){
		try {
			return new VectorR2(toVector() .$D$ (s));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public VectorR2 $E$(double s){
		try {
			return new VectorR2(toVector() .$E$ (s));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public VectorR2 sq(){
		try {
			return new VectorR2(toVector() .sq ());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Vector toVector() {
		return new Vector(new Tuple(new double[] {mX,mY}));
	}
	
	public static VectorR2 New(PointR2 base, PointR2 end) {
		try {
			return new VectorR2(base,end);
		} catch (Exception e) {
			//this will never happen
			return null;
		}
	}
	
	public static VectorR2 New(Vector v){
		try {
			return new VectorR2(v);
		} catch (Exception e){
			//this will never happen
			return null;
		}
	}
	
	public void move(PointR2 pos) {
		try {
			VectorR2 newVector=new VectorR2(pos,new PointR2(pos.getX()+getMagnetudeX(),pos.getY()+getMagnetudeY()));
			this. $X$ (0). $A$ (newVector);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rotate(double degrees) {
		PointR2 pos=base();
		move(new PointR2(0,0));
		try {
			VectorR2 newVector=new VectorR2(mX*Math.cos(degrees)-mY*Math.sin(degrees),mY*Math.cos(degrees)+mX*Math.sin(degrees));//rotate about origin
			this. $X$ (0). $A$ (newVector).move(pos);//set to new vector and move back to original position
		} catch (Exception e) {
			//will never happen
		}
	}
}
