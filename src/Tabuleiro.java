public class Tabuleiro {
	Campo[][] campos;
	int n;
	
	void gerarTabuleiro() {
		this.n = 8;
		campos = new Campo[n][n];
		
		//Começamos gerando o espaço total
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				campos[i][j] = new Campo(i, j, false, false);
			}
		}
		
		//Incluimos as peças pretas
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < n; j++) {
				if ((i+j)%2 == 1)
					campos[i][j].colocarPeca(true);
			}
		}
		
		//Incluimos as peças brancas
		for (int i = 5; i < 8; i++) {
			for (int j = 0; j < n; j++) {
				if ((i+j)%2 == 1)
					campos[i][j].colocarPeca(false);
			}
		}
	}
	
	void aplicarMovimento(String commands) {
		//Processamos o comando de entrada
		char[] command = commands.toCharArray();
		int sourceX, sourceY, targetX, targetY;
		boolean preta;
		
		sourceX = n - (command[1] - '0');
		targetX = n - (command[4] - '0');
		
		sourceY = command[0] - 'a';
		targetY = command[3] - 'a';
		
		preta = campos[sourceX][sourceY].getPreta();
		campos[sourceX][sourceY].comerPeca();
		campos[targetX][targetY].colocarPeca(preta);		
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
		//Na última linha, imprimimos as letras
		System.out.println("  a b c d e f g h ");
		System.out.println();
	}	
}
