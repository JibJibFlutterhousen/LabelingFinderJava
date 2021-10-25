public class Labeling_Finder_Data{
	Edge_Relation k_4_snake_edge_relation_object;
	Edge_Relation k_5_snake;
	Edge_Relation windmill_edge_relation_object;
	Edge_Relation fancy_pentagon_object;
	Edge_Relation fancy_hexagon_object;
	Edge_Relation fancy_octagon_object;
	Edge_Relation fancy_crab_object;
	Edge_Relation lopsided_fancy_crab;
	Edge_Relation fancy_fish;
	Edge_Relation fancy_emerald_object;
	Edge_Relation c_20;
	int[] U11 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] U15 = { 1, 2, 4, 7, 8, 11, 13, 14 };
	int[] U21 = { 1, 2, 4, 5, 8, 10, 11, 13, 16, 17, 19, 20 };
	int[] U32 = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31 };
	int[] U33 = { 1, 2, 4, 5, 7, 8, 10, 13, 14, 16, 17, 19, 20, 23, 25, 26, 28, 29, 31, 32 };

	public String get_graph_names(){
		String output = "";

		output += 
		"K_4 Snake" +
		"K_5 Snake" +
		"Windmill with 9 nodes" +
		"5 pointed wheel" +
		"6 pointed wheel" +
		"8 pointed wheel" +
		"Fancy crab" +
		"Lopsided fancy crab" +
		"Fancy fish" +
		"Fancy emerald"
		;

		return output;
	}

	public int[] get_labeling_set(String input_request){
		int[] output;
		Edge_Relation relation_to_use = get_edge_relation(input_request);

		if(relation_to_use.get_number_of_edges() == 8){
			output = U15;
		}else if(relation_to_use.get_number_of_edges() == 10){
			output = U11;
		}else if(relation_to_use.get_number_of_edges() == 12){
			output = U21;
		}else if(relation_to_use.get_number_of_edges() == 16){
			output = U32;
		}else if(relation_to_use.get_number_of_edges() == 20){
			output = U33;
		}else{
			System.out.printf("Graph not found!%n");
			output = new int[0];
		}

		return output;
	}

	public Edge_Relation get_edge_relation(String input_request){
		if(input_request == "K_4 Snake"){
			return k_4_snake_edge_relation_object;
		}else if(input_request == "K_5 Snake"){
			return k_5_snake;
		}else if(input_request == "Windmill with 9 nodes"){
			return windmill_edge_relation_object;
		}else if(input_request == "5 pointed wheel"){
			return fancy_pentagon_object;
		}else if(input_request == "6 pointed wheel"){
			return fancy_hexagon_object;
		}else if(input_request == "8 pointed wheel"){
			return fancy_octagon_object;
		}else if(input_request == "Fancy crab"){
			return fancy_crab_object;
		}else if(input_request == "Lopsided fancy crab"){
			return lopsided_fancy_crab;
		}else if(input_request == "Fancy fish"){
			return fancy_fish;
		}else if(input_request == "Fancy emerald"){
			return fancy_emerald_object;
		}else{
			System.out.printf("Graph not found!%n");
			return new Edge_Relation(1);
		}
	}
	public int get_number_of_vertexes_in(String input_request){
		if(input_request == "K_4 Snake"){
			return 7;
		}else if(input_request == "K_5 Snake"){
			return 9;
		}else if(input_request == "Windmill with 9 nodes"){
			return 9;
		}else if(input_request == "5 pointed wheel"){
			return 6;
		}else if(input_request == "6 pointed wheel"){
			return 7;
		}else if(input_request == "8 pointed wheel"){
			return 9;
		}else if(input_request == "Fancy crab"){
			return 9;
		}else if(input_request == "Lopsided fancy crab"){
			return 8;
		}else if(input_request == "Fancy fish"){
			return 6;
		}else if(input_request == "Fancy emerald"){
			return 7;
		}else{
			System.out.printf("Graph not found!%n");
			return -1;
		}
	}

	public Labeling_Finder_Data(){
		/*
			this corresponds to K_4 snake 
		*/
		k_4_snake_edge_relation_object = new Edge_Relation(12);
			k_4_snake_edge_relation_object.add_relation(0, 1);
			k_4_snake_edge_relation_object.add_relation(0, 2);
			k_4_snake_edge_relation_object.add_relation(0, 6);
			k_4_snake_edge_relation_object.add_relation(1, 2);
			k_4_snake_edge_relation_object.add_relation(1, 6);
			k_4_snake_edge_relation_object.add_relation(6, 2);
			k_4_snake_edge_relation_object.add_relation(3, 4);
			k_4_snake_edge_relation_object.add_relation(3, 2);
			k_4_snake_edge_relation_object.add_relation(3, 5);
			k_4_snake_edge_relation_object.add_relation(4, 2);
			k_4_snake_edge_relation_object.add_relation(4, 5);
			k_4_snake_edge_relation_object.add_relation(5, 2);

		/*
			This responds to the windmil
		*/
		windmill_edge_relation_object = new Edge_Relation(12);
			windmill_edge_relation_object.add_relation(0, 1);
			windmill_edge_relation_object.add_relation(0, 2);
			windmill_edge_relation_object.add_relation(0, 3);
			windmill_edge_relation_object.add_relation(0, 4);
			windmill_edge_relation_object.add_relation(0, 5);
			windmill_edge_relation_object.add_relation(0, 6);
			windmill_edge_relation_object.add_relation(0, 7);
			windmill_edge_relation_object.add_relation(0, 8);
			windmill_edge_relation_object.add_relation(1, 2);
			windmill_edge_relation_object.add_relation(3, 4);
			windmill_edge_relation_object.add_relation(5, 6);
			windmill_edge_relation_object.add_relation(7, 8);

		/*
			This corresponds to the fancy pentagon
		*/
		fancy_pentagon_object = new Edge_Relation(10);
			fancy_pentagon_object.add_relation(0, 1);
			fancy_pentagon_object.add_relation(0, 2);
			fancy_pentagon_object.add_relation(0, 3);
			fancy_pentagon_object.add_relation(0, 4);
			fancy_pentagon_object.add_relation(0, 5);
			fancy_pentagon_object.add_relation(1, 2);
			fancy_pentagon_object.add_relation(2, 3);
			fancy_pentagon_object.add_relation(3, 4);
			fancy_pentagon_object.add_relation(4, 5);
			fancy_pentagon_object.add_relation(5, 1);

		/*
			this is the fancy hexagon
		*/
		fancy_hexagon_object = new Edge_Relation(12);
			fancy_hexagon_object.add_relation(0, 1);
			fancy_hexagon_object.add_relation(0, 2);
			fancy_hexagon_object.add_relation(0, 3);
			fancy_hexagon_object.add_relation(0, 4);
			fancy_hexagon_object.add_relation(0, 5);
			fancy_hexagon_object.add_relation(0, 6);
			fancy_hexagon_object.add_relation(1, 2);
			fancy_hexagon_object.add_relation(2, 3);
			fancy_hexagon_object.add_relation(3, 4);
			fancy_hexagon_object.add_relation(4, 5);
			fancy_hexagon_object.add_relation(5, 6);
			fancy_hexagon_object.add_relation(6, 1);

		/*
			this is the fancy octogon
		*/
		fancy_octagon_object = new Edge_Relation(16);
			fancy_octagon_object.add_relation(0, 1);
			fancy_octagon_object.add_relation(0, 2);
			fancy_octagon_object.add_relation(0, 3);
			fancy_octagon_object.add_relation(0, 4);
			fancy_octagon_object.add_relation(0, 5);
			fancy_octagon_object.add_relation(0, 6);
			fancy_octagon_object.add_relation(0, 7);
			fancy_octagon_object.add_relation(0, 8);
			fancy_octagon_object.add_relation(1, 2);
			fancy_octagon_object.add_relation(2, 3);
			fancy_octagon_object.add_relation(3, 4);
			fancy_octagon_object.add_relation(4, 5);
			fancy_octagon_object.add_relation(5, 6);
			fancy_octagon_object.add_relation(6, 7);
			fancy_octagon_object.add_relation(7, 8);
			fancy_octagon_object.add_relation(8, 1);

		/*
			this is the fancy crab
		*/
		fancy_crab_object = new Edge_Relation(12);
			fancy_crab_object.add_relation(0, 1);
			fancy_crab_object.add_relation(0, 2);
			fancy_crab_object.add_relation(1, 2);
			fancy_crab_object.add_relation(1, 4);
			fancy_crab_object.add_relation(3, 4);
			fancy_crab_object.add_relation(4, 5);
			fancy_crab_object.add_relation(4, 7);
			fancy_crab_object.add_relation(4, 6);
			fancy_crab_object.add_relation(6, 7);
			fancy_crab_object.add_relation(6, 8);
			fancy_crab_object.add_relation(7, 8);
			fancy_crab_object.add_relation(2, 4);

		/*
			this is the lopsided fancy crab
		*/
		lopsided_fancy_crab = new Edge_Relation(12);
			lopsided_fancy_crab.add_relation(0, 1);
			lopsided_fancy_crab.add_relation(0, 2);
			lopsided_fancy_crab.add_relation(1, 2);
			lopsided_fancy_crab.add_relation(2, 3);
			lopsided_fancy_crab.add_relation(3, 4);
			lopsided_fancy_crab.add_relation(1, 3);
			lopsided_fancy_crab.add_relation(0, 4);
			lopsided_fancy_crab.add_relation(3, 5);
			lopsided_fancy_crab.add_relation(3, 6);
			lopsided_fancy_crab.add_relation(6, 7);
			lopsided_fancy_crab.add_relation(5, 6);
			lopsided_fancy_crab.add_relation(5, 7);
		
		/*
			this is the lopsided fancy crab
		*/
		fancy_fish = new Edge_Relation(8);
			fancy_fish.add_relation(0, 1);
			fancy_fish.add_relation(0, 2);
			fancy_fish.add_relation(1, 2);
			fancy_fish.add_relation(2, 3);
			fancy_fish.add_relation(2, 4);
			fancy_fish.add_relation(3, 4);
			fancy_fish.add_relation(3, 5);
			fancy_fish.add_relation(4, 5);

		/*
			this is the fancy emerald derived from K_4 snake
		*/
		fancy_emerald_object = new Edge_Relation(12);
			fancy_emerald_object.add_relation(0, 1);
			fancy_emerald_object.add_relation(0, 2);
			fancy_emerald_object.add_relation(0, 3);
			fancy_emerald_object.add_relation(1, 4);
			fancy_emerald_object.add_relation(1, 3);
			fancy_emerald_object.add_relation(1, 2);
			fancy_emerald_object.add_relation(2, 3);
			fancy_emerald_object.add_relation(2, 5);
			fancy_emerald_object.add_relation(3, 4);
			fancy_emerald_object.add_relation(3, 5);
			fancy_emerald_object.add_relation(6, 4);
			fancy_emerald_object.add_relation(6, 5);

		/*
			this is K_5 snake
		*/
		k_5_snake = new Edge_Relation(20);
			k_5_snake.add_relation(4, 0);
			k_5_snake.add_relation(4, 1);
			k_5_snake.add_relation(4, 2);
			k_5_snake.add_relation(4, 3);
			k_5_snake.add_relation(4, 5);
			k_5_snake.add_relation(4, 6);
			k_5_snake.add_relation(4, 7);
			k_5_snake.add_relation(4, 8);
			k_5_snake.add_relation(0, 1);
			k_5_snake.add_relation(0, 2);
			k_5_snake.add_relation(0, 3);
			k_5_snake.add_relation(1, 2);
			k_5_snake.add_relation(1, 3);
			k_5_snake.add_relation(2, 3);
			k_5_snake.add_relation(8, 5);
			k_5_snake.add_relation(8, 6);
			k_5_snake.add_relation(8, 7);
			k_5_snake.add_relation(5, 6);
			k_5_snake.add_relation(5, 7);
			k_5_snake.add_relation(6, 7);

		/*
			this is C_20
		*/
		c_20 = new Edge_Relation(20);
			c_20.add_relation(0, 1);
			c_20.add_relation(1, 2);
			c_20.add_relation(2, 3);
			c_20.add_relation(3, 4);
			c_20.add_relation(4, 5);
			c_20.add_relation(5, 6);
			c_20.add_relation(6, 7);
			c_20.add_relation(7, 8);
			c_20.add_relation(8, 9);
			c_20.add_relation(9, 10);
			c_20.add_relation(10, 11);
			c_20.add_relation(11, 12);
			c_20.add_relation(12, 13);
			c_20.add_relation(13, 14);
			c_20.add_relation(14, 15);
			c_20.add_relation(15, 16);
			c_20.add_relation(16, 17);
			c_20.add_relation(17, 18);
			c_20.add_relation(18, 19);
			c_20.add_relation(19, 0);
		/*

		*/
	}

}