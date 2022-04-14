package com.tui;
import java.util.*;
public class Gol{
	int cols;
	int rows;
	//print the graph
	public void print_graph(int[][]graph,String alive,String dead,int gen){
		System.out.println("Gen : "+gen);
		for(int a=0;a<rows;a++){
			for(int b=0;b<cols;b++){
				System.out.print(graph[a][b]==1?alive:dead);
			}
			System.out.println();
		
		}
		System.out.print("\033["+(this.rows+1)+"A");
	}
	
	public int[][] draw_graph(int [][]graph){
		java.util.Random rd=new Random();
		for(int a=0;a<this.rows;a++){
			for(int b=0;b<this.cols;b++){
				if(rd.nextBoolean() && rd.nextBoolean()){
					graph[a][b]=rd.nextBoolean()?0:1;
				}
			}
		}
		return graph;
	}

	public int[][] copy_graph(int [][]graph){
		int tmp[][]=new int[this.rows][this.cols];
		for(int a=0;a<this.rows;a++){
			for(int b=0;b<this.cols;b++){
				tmp[a][b]=graph[a][b];
			}
		}
		return tmp;
	}


	public int[][] god(int[][] graph){
		int tmp[][]=this.copy_graph(graph);
		//int tmp[][]=new int{graph.clone()};
		for(int a=0;a<this.rows;a++){
			for(int b=0;b<this.cols;b++){
				//System.out.println(this.rows);
				int neighbours[]=new int[8];

				int top	= (a-1>=0)?(a-1):-1;
				int down= ((a+1)<this.rows)?(a+1):-1;
				int left= (b-1)>=0?(b-1):-1;
				int right= ((b+1)<this.cols)?(b+1):-1;
				/////////////////////////////////
				neighbours[0]=(top!=-1)?graph[top][b]:0;		//top
				neighbours[1]=(down!=-1)?graph[down][b]:0;		//down
				//System.out.println(down + " "+b);
				//System.out.println(graph.length);
				neighbours[2]=(left!=-1)?graph[a][left]:0;		//left
				neighbours[3]=(right!=-1)?graph[a][right]:0;		//right
				//System.out.println(right);
				neighbours[4]=(top!=-1 && left!=-1)?graph[top][left]:0;//top_left
				neighbours[5]=(top!=-1 && right!=-1)?graph[top][right]:0;//top_right
				neighbours[6]=(down!=-1 && left!=-1)?graph[down][left]:0; //down_left
				neighbours[7]=(down!=-1 && right!=-1)?graph[down][right]:0;			//down_right
				int alive_neighbours=0;
				for(int c=0;c<8;c++){
					alive_neighbours += (neighbours[c]!=0)?1:0;
					
					//System.out.println(alive_neighbours);
				}		
				if(graph[a][b]==1 && alive_neighbours<2){
					tmp[a][b]=0;
				}
				else if (graph[a][b]==1 && (alive_neighbours==2||alive_neighbours==3)){
					tmp[a][b]=1;
				} 
				else if(graph[a][b]==1 && alive_neighbours>3){
					tmp[a][b]=0;
				}
				else if(graph[a][b]==0 && alive_neighbours==3){
					tmp[a][b]=1;
				}
		
			}
		}				
		graph=this.copy_graph(tmp);
		

		return graph;
	}
	public int [][] add_artifical_cell(int [][]graph,int n){
		Random rd=new Random();
		for(int a=0;a<n;a++){
			graph[rd.nextInt(this.rows)][rd.nextInt(this.cols)]=1;
		}
		return graph;
	
	}



	Gol(int rows,int cols){
		this.rows=rows;
		this.cols=cols;
		int [][]graph=new int[this.rows][this.cols];
		graph=this.draw_graph(graph);
		//this.print_graph(graph,'#',' ');
		Random rd=new Random();
		String alive="●"; //"🏾";
		String dead= " ";//"🏻";
		int gen=0;
		//init for artifical cells;
		int n_cache=10;
		int [][][]t=new int[n_cache+2][this.rows][this.cols];		
		int n=20;
		int a=0;
		for(int d=0;d<this.rows;d++){
			for (int e=0;e<this.cols;e++){
				t[n_cache][d][e]=0;
			}
		}

		while (true){

			try{
				Thread.sleep(0);
			}catch(Exception e){
			
				System.out.println(e);
			}

			graph=this.god(graph);



			// if there is no move add artiffical moves
			for(int b=0;b<n_cache;b++){
				if(Arrays.deepEquals(graph,t[b])){
					gen++;
					graph=this.add_artifical_cell(graph,n);
				}
			}
			if (Arrays.deepEquals(graph,t[n_cache+1])){
				graph=this.draw_graph(graph);
				gen++;
			}
			this.print_graph(graph,alive,dead,gen);
			//this.print_graph(graph,'ဝ',' ');
			
			for(int c=0;c<n_cache;c++){
				if(a==c){
					t[a]=copy_graph(graph);
				}
				
				if(a>=n_cache){
					a=-1;
				}
			}
			a++;
			
		}
			
		

		
	}











	public static void main(String[]args){
		new Gol(23,91);

	



	}

}
