public class Campo {
	protected int x;
	protected int y;
	protected boolean preta;// false se for branca B, true se for preta P
	protected Comum comum;
	
	public Campo (int x, int y) {
		this.x = x;
		this.y = y;
		comum = null;
	}
	
	public boolean getPreta() {
		return preta;
	}
	
	public Comum getComum() {
		return comum;
	}
	
	public void colocarPeca(Comum comum) {
		this.comum = comum;
	}
	
	public void comerPeca() {
		this.comum = null;
	}
	
	public void mostrarCampo() {
		if (comum == null) {
			System.out.print("- ");
		} else {
			if(comum.preta == false) {
				System.out.print("B ");
			}
			else {
				System.out.print("P ");
			}
		}
	}
}
