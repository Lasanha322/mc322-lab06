public class Campo {
	private int x;
	private int y;
	private boolean peca; //false se for um espaco vazio, true se for um espaco pra peca
	private boolean comida; //false quanto a peça nesse espaco nao foi comida, true quando foi comida
	private boolean preta;// false se for branca B, true se for preta P
	
	public Campo (int x, int y, boolean peca,boolean preta) {
		this.x = x;
		this.y = y;
		this.peca = peca;
		this.comida = false;
		this.preta = preta;
	}
	
	public void colocarPeca(boolean preta) {
		peca = true;
		comida = false;
		this.preta = preta;
	}
	
	public void comerPeca() {
		comida = true;
	}
	
	public void mostrarCampo() {
		if (peca == false) {
			System.out.print("  ");
		} else if (comida == false) {
			if(preta == false) {
				System.out.print("B ");
			}
			else {
				System.out.print("P ");
			}
		} else {
			System.out.print("- ");
		}
	}
}
