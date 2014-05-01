package com.myperfectgame.blocks;

import org.newdawn.slick.Image;

public abstract class AbstractBlock<T> implements Block<T> {
	
	protected T[][] blocks; // = new boolean[4][4];
	
	@Override
	public T[][] rotateClockWise() {

		T tmp;
		int n=4;

        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                tmp = blocks[i][j];
                if(tmp instanceof Image) {
                  //  ((Image) tmp).setCenterOfRotation(((Image) tmp).getWidth() / 2, ((Image) tmp).getHeight() / 2);

                    ((Image) tmp).setRotation(((Image) tmp).getRotation() - 90);
                    blocks[i][j] = tmp;
                }

            }
        }

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
	
	public T getBlock(int i, int j) {
		return blocks[i][j];
		
	}

	@Override
	public T[][] rotateCounterClockWise() {

        T tmp;
		int n=4;
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                tmp = blocks[i][j];
                if(tmp instanceof Image) {
                   // ((Image) tmp).setCenterOfRotation(((Image) tmp).getWidth() / 2, ((Image) tmp).getHeight() / 2);

                    ((Image) tmp).setRotation(((Image) tmp).getRotation() + 90);
                    blocks[i][j] = tmp;
                }

            }
        }

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
	public T[][] getBlocks() {
		return blocks;
	}
	
	
	@Override
	public String toString() {
		String tmp = new String();
		for (int i=0; i<4; i++){
			for (int j=0; j<4; j++){
				tmp += blocks[i][j].toString();
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
