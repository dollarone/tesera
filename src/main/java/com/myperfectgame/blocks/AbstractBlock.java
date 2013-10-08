package com.myperfectgame.blocks;

public abstract class AbstractBlock implements Block {
	
	protected boolean[][] blocks; // = new boolean[4][4];
	
	@Override
	public boolean[][] rotateClockWise() {
		
		boolean tmp;
		int n=4;
		
		// vector rotation algorithm
		for (int i=0; i<n/2; i++){
			for (int j=i; j<n-i-1; j++){
			
				tmp = blocks[n-j-1][i];
				blocks[n-j-1][i] = blocks[n-i-1][n-j-1];
				blocks[n-i-1][n-j-1] = blocks[j][n-i-1];
				blocks[j][n-i-1] = blocks[i][j];
				blocks[i][j] = tmp;
			}
		}
		//this.blocks = blocks;
		return blocks;
	}
	
	public boolean getBlock(int i, int j) {
		return blocks[i][j];
		
	}

	@Override
	public boolean[][] rotateCounterClockWise() {
		
		boolean tmp;
		int n=4;
		
		// vector rotation algorithm
		for (int i=0; i<n/2; i++){
			for (int j=i; j<n-i-1; j++){
				tmp = blocks[i][j];
				blocks[i][j] = blocks[j][n-i-1];
				blocks[j][n-i-1] = blocks[n-i-1][n-j-1];
				blocks[n-i-1][n-j-1] = blocks[n-j-1][i];
				blocks[n-j-1][i] = tmp;
			}
		}
		this.blocks = blocks;
		return blocks;
	}

	@Override
	public boolean[][] getBlocks() {
		return blocks;
	}
	
	
	@Override
	public String toString() {
		String tmp = new String();
		for (int i=0; i<4; i++){
			for (int j=0; j<4; j++){
				tmp += blocks[i][j] ? "[X]" : "[ ]"; 
			}
			tmp += "\n";
		}
		
		return tmp; 
	}

	/*
	the 7 different traditional blocks:
	XX X XX X XX X XX X X XX X XX XX X
	XX X XX X X  X X  X X XX X  X X  X
	XX X X  X XX X X XX X  X XX X X XX
	XX X XXXX XXXX XXXX XXXX XXXX XXXX
	*/
	

}
