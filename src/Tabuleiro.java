public class Tabuleiro {
	protected Campo[][] campos;
	protected int n;
	
	void gerarTabuleiro() {
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
		
		if (campos[sourceX][sourceY].getComum() != null && campos[sourceX][sourceY].comum.movimento(targetX, targetY, campos)) {
			campos[targetX][targetY].colocarComum(campos[sourceX][sourceY].getComum());
			campos[sourceX][sourceY].comerPeca();
			campos[targetX][targetY].comum.setCoord(targetX, targetY);

			comerPecas(sourceX, sourceY, targetX, targetY);
			
			if (targetX == 0 && campos[targetX][targetY].comum.getPreta() == false) {
				campos[targetX][targetY].comerPeca();
				campos[targetX][targetY].colocarDama(new Dama(targetX, targetY, false));				
			} else if (targetX == 7 && campos[targetX][targetY].comum.getPreta() == true) {
				campos[targetX][targetY].comerPeca();
				campos[targetX][targetY].colocarDama(new Dama(targetX, targetY, true));				
			}
		} else if (campos[sourceX][sourceY].getDama() != null && campos[sourceX][sourceY].dama.movimento(targetX, targetY, campos)) {
			campos[targetX][targetY].colocarDama(campos[sourceX][sourceY].getDama());
			campos[sourceX][sourceY].comerPeca();
			campos[targetX][targetY].dama.setCoord(targetX, targetY);
			
			comerPecas(sourceX, sourceY, targetX, targetY);
		}
	}
	
	void comerPecas(int sourceX, int sourceY, int targetX, int targetY) {
		int distX, distY, fatorX, fatorY;
		
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
			campos[targetX-distX][targetY-distY].comerPeca();
			distX = distX - fatorX;
			distY = distY - fatorY;
		}		
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