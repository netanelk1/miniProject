package primitives;

public class Material {
	protected double  kD;
	protected double  kS;
	protected int nShininess;
	protected double kR;
	protected double kT;
	
	
	 /**
	  * constructor of Material
	  * @param kD
	  * @param kS
	  * @param nShininess
	  * @param kR
	  * @param kT
	  */
	public Material(double kD, double kS, int nShininess,double kR,double kT) {
		super();
		this.kD = kD;
		this.kS = kS;
		this.nShininess = nShininess;
		this.kR=kR;
		this.kT=kT;
		
	}
	
	/**
	 * copy constructor of Material
	 * @param material
	 * 					the object we want to copy
	 */
	public Material(Material material)
	{
		this.kD=material.kD;
		this.kS=material.kS;
		this.nShininess=material.nShininess;
		this.kR=material.kR;
		this.kT=material.kT;
	}
	
	
	/**getters setters*/
	public double getKd() {
		return kD;
	}
	
	public double getKs() {
		return kS;
	}
	
	public int getnShininess() {
		return nShininess;
	}


	public double getKr() {
		return kR;
	}



	public double getKt() {
		return kT;
	}


	
	
	

}
