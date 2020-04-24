public class Tabuleiro {
	protected Campo[][] campos;
	protected boolean turno; //false para indicar turno da branca, true para indicar turno da preta
	protected int n;
	protected int comeuX, comeuY; //coordenadas da ultima peca que comeu outra
	
	void gerarTabuleiro() {
		this.turno = false;
		this.n = 8;
		campos = new Campo[n][n];
		Comum comum;
		
		//Comecamos gerando o espaco total
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				campos[i][j] = new Campo(i, j);
			}
		}
		
		//Incluimos as pecas pretas
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < n; j++) {
				if ((i+j)%2 == 1) {
					comum = new Comum(i, j, true);
					campos[i][j].colocarComum(comum);
				}
			}
		}
		
		//Incluimos as pecas brancas
		for (int i = 5; i < 8; i++) {
			for (int j = 0; j < n; j++) {
				if ((i+j)%2 == 1) {
					comum = new Comum(i, j, false);
					campos[i][j].colocarComum(comum);
				}
			}
		}
	}
	
	void aplicarMovimento(String commands) {
		//Processamos o comando de entrada
		char[] command = commands.toCharArray();
		int sourceX, sourceY, targetX, targetY;
		
		sourceX = n - (command[1] - '0');
		targetX = n - (command[4] - '0');
		
		sourceY = command[0] - 'a';
		targetY = command[3] - 'a';
		
		//Verificamos se eh uma jogada duma peca comum ou dama
		if (campos[sourceX][sourceY].getComum() != null && 
			campos[sourceX][sourceY].comum.movimento(targetX, targetY, campos)) {
			//Verificamos se eh jogada sucessiva de uma peca que jah comeu
			if (sourceX == comeuX && sourceY == comeuY) {
				//Soh jogamos se ele for comer uma peca
				if(comerPecas(sourceX, sourceY, targetX, targetY)) {
					comeuX = targetX;
					comeuY = targetY;
					
					campos[targetX][targetY].colocarComum(campos[sourceX][sourceY].getComum());
					campos[sourceX][sourceY].comerPeca();
					campos[targetX][targetY].comum.setCoord(targetX, targetY);
					
					//Verificamos se ela deve virar dama
					if (targetX == 0 && campos[targetX][targetY].comum.getPreta() == false) {
						campos[targetX][targetY].comerPeca();
						campos[targetX][targetY].colocarDama(new Dama(targetX, targetY, false));				
					} else if (targetX == 7 && campos[targetX][targetY].comum.getPreta() == true) {
						campos[targetX][targetY].comerPeca();
						campos[targetX][targetY].colocarDama(new Dama(targetX, targetY, true));				
					}			
				}				
			} 
			//Se nao for, verificamos se ta no turno dele
			else if (campos[sourceX][sourceY].comum.getPreta() == this.turno) {
				if(comerPecas(sourceX, sourceY, targetX, targetY)) {
					comeuX = targetX;
					comeuY = targetY;
				} else {
					comeuX = 0;
					comeuY = 0;
				}
				campos[targetX][targetY].colocarComum(campos[sourceX][sourceY].getComum());
				campos[sourceX][sourceY].comerPeca();
				campos[targetX][targetY].comum.setCoord(targetX, targetY);
				this.turno = !this.turno;
				
				//Verificamos se ela deve virar dama
				if (targetX == 0 && campos[targetX][targetY].comum.getPreta() == false) {
					campos[targetX][targetY].comerPeca();
					campos[targetX][targetY].colocarDama(new Dama(targetX, targetY, false));				
				} else if (targetX == 7 && campos[targetX][targetY].comum.getPreta() == true) {
					campos[targetX][targetY].comerPeca();
					campos[targetX][targetY].colocarDama(new Dama(targetX, targetY, true));				
				}				
			} 
			//Nos outros casos, a jogada eh invalida, entao nao computamos nada
		} else if (campos[sourceX][sourceY].getDama() != null &&
			campos[sourceX][sourceY].dama.movimento(targetX, targetY, campos)) {
			//Verificamos se eh jogada sucessiva de uma peca que jah comeu
			if (sourceX == comeuX && sourceY == comeuY) {
				//Soh jogamos se ele for comer uma peca
				System.out.println("comeu, joga dnv");
				if(comerPecas(sourceX, sourceY, targetX, targetY)) {
					comeuX = targetX;
					comeuY = targetY;
					
					campos[targetX][targetY].colocarDama(campos[sourceX][sourceY].getDama());
					campos[sourceX][sourceY].comerPeca();
					campos[targetX][targetY].dama.setCoord(targetX, targetY);
				}				
			} 
			//Se nao for, verificamos se ta no turno dele
			else if (campos[sourceX][sourceY].dama.getPreta() == this.turno) {
				if(comerPecas(sourceX, sourceY, targetX, targetY)) {
					comeuX = targetX;
					comeuY = targetY;
				} else {
					comeuX = 0;
					comeuY = 0;
				}
				campos[targetX][targetY].colocarDama(campos[sourceX][sourceY].getDama());
				campos[sourceX][sourceY].comerPeca();
				campos[targetX][targetY].dama.setCoord(targetX, targetY);
				this.turno = !this.turno;			
			} 
			//Nos outros casos, a jogada eh invalida, entao nao computamos nada
		}
	}
	
	boolean comerPecas(int sourceX, int sourceY, int targetX, int targetY) {
		int distX, distY, fatorX, fatorY;
		boolean flagComeu = false; //Anota se alguma peca foi realmente comida nesse trajeto
		
		distX = targetX - sourceX;
		if (distX < 0)
			fatorX = -1;
		else
			fatorX = 1;
		
		distY = targetY - sourceY;
		if (distY < 0)
			fatorY = -1;
		else
			fatorY = 1;
		
		while(distX != 0 && distY != 0) {
			distX = distX - fatorX;
			distY = distY - fatorY;
			if (campos[targetX-distX][targetY-distY].getComum() != null ||
				campos[targetX-distX][targetY-distY].getDama() != null)
				flagComeu = true;
			campos[targetX-distX][targetY-distY].comerPeca();
		}
		
		return flagComeu;
	}
	
	void imprimirTabuleiro() {
		//Fazemos um loop para cada linha que sera impressa
		for (int i = 0; i < n; i++) {
			//Pra cada linha, imprimimos os elementos nas colunas
			System.out.print(n-i + " ");
			for (int j = 0; j < n; j++) {
				campos[i][j].mostrarCampo();
			}
			System.out.println();
		}
		
		//Na ultima linha, imprimimos as letras
		System.out.println("  a b c d e f g h ");
		System.out.println();
	}	
}