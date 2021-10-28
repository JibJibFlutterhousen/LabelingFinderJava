import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.BigInteger;
import java.math.*;
import java.util.Locale;
import java.text.NumberFormat;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Labeling_Finder_Utilities{
/*
	This function returns the factorial of a given number

	this function needs:
	import java.math.BigInteger;
*/
	public static BigInteger factorial(int input_n){
		BigInteger output = new BigInteger("1");
		for(int i = 1; i <= input_n; i++){
			output = output.multiply(new BigInteger(String.format("%d", i)));
		}
		return output;
	}
/*
	This function returns an integer array with the input value removed
*/
	public static int[] remove_value_from_int_array(int[] initial_array, int value_to_remove){
		int[] output;
		/*
			We will copy the array (except the value(s) that we wanted to remove) into a list first, so we can dynamicaly size the output array based off of the size we need
		*/
		List<Integer> output_list = new ArrayList<>();
		for(int i = 0; i < initial_array.length; i++){
			if(!(initial_array[i] == value_to_remove)){
				output_list.add(initial_array[i]);
			}
		}/*
			We then create the array we will be returning, and returning it
		*/
		output = new int[output_list.size()];
		for(int i = 0; i < output_list.size(); i++){
			output[i] = output_list.get(i);
		}
		return output;
	}
/*
	This function takes an input element, and adds it to an input array.

	The functionality of this function is to "slide" all the elements over by one, making space for the given element
*/
	public static int[] Int_Array_Add_On_Left(int input_element, int[] input_array){
		int[] output = new int[input_array.length+1];
		output[0] = input_element;
		for(int i = 1; i < input_array.length+1; i++){
			output[i] = input_array[i-1];
		}
		return output;
	}
/*
	this function is the primary permutation generator. this is where we have to "prune" off the labeling list to only use the first number_to_use numbers. This is a formality since the number of edges on a graph is what we're trying to make a rainbow, while the number of vertexes may be far fewer than the number of edges

	Since it's gotten rather lengthy, here's the argument list and why they're there:
		previous_valud_permutation
			This is a List<Integer> and represents the last valid permutation that was tried. This is needed to make sure that we aren't creating duplicate labelings.
		current_workingList
			This is also a List<Integer> and represents the permutation that we're currently building. We recursively build this, so it is passed from iteration to iteration.
		array_to_permute
			This is a int[] and represents the input array that we are choosing permutations from. This isn't destructively touched through the iterations, because we use current_working_list as a destructable version of this array.
		used_indexes
			This is a boolean[] and represents the indexes of array_to_permute that have been used. This is neccesary so we can keep track of which indexes have been used so we don't label multiple vertexes with the same value. This is necessary because we aren't destructively altering array_to_permute.
		number_to_use
			This is a int and represents the number of vertexes on the graph (aside from the one that we're fixing)
		input_edge_relation
			This is a Edge_Relation and represents the graph we are labeling. This is neccesary since we'll be creating the graph within this funciton. We initially were just creating the list of permutations, but since that'd take too much RAM for the larger graphs (more than 10 vertexes) to be feasible.
		input_modulo
			This is the modulo that we are working under when creating the graph. This is neccesary for the same reason that the input_edge_relation is needed.
		input_fixed_labeling
			This is a int and represents a labeling that is assigned to vertex with name 0 on the graph. This is needed because we want to multithread the process, and this is how we will be determining different threads.
		loop_counter
			This is a Big_Integer_Counter and represents the number of iterations we have tested a graph. This isn't neccesary for proper operation, but for diagnostic reasons we include it.
		output_path
			This is a String and represents where the output will be pushed. This is neccesary because the parralellized nature of this will mean that the command prompt window may be updated out of order, leading to confusion of the user.
		input_thread
			this is the thread that will be executing the iteration of this method. This is neccesary because system.exit() stops all of the currently running threads.

	this function needs:
		import java.math.*;
		import java.util.Locale;
		import java.text.NumberFormat;
		import java.util.ArrayList;
		import java.util.Arrays;
		import java.util.List;
*/
	public static Graph test_all_labelings(List<Integer> previous_valid_permutation, List<Integer> current_working_list, int[] array_to_permute, boolean[] used_indexes, int number_to_use, Edge_Relation input_edge_relation, int input_modulo, int input_fixed_labeling, Big_Integer_Counter loop_counter, String output_path, Thread input_thread){
		Graph output = new Graph();
		// try{
		// 	File file_to_create = new File(output_path);
		// 	file_to_create.createNewFile();
		// 	try(FileWriter  output_stream = new FileWriter (output_path)){
				if(current_working_list.size() == number_to_use){
					/*
						We've gathered enough elements to try and permute the things, first make sure it's not a duplicate
					*/
					if(!current_working_list.equals(previous_valid_permutation)){
						/*
							at this point, we have gathered enough elements to fill up all the vertexes except the fixed one, so we will add that one in now!
						*/
						current_working_list.add(input_fixed_labeling);
						/*
							now we've got a potentially valid labeling, so we will check it!
						*/
						loop_counter.iterate();
						output = new Graph(new Vertex_Set(new Labeling_Set(current_working_list)), input_edge_relation, input_modulo);
						/*
							progress updater: update every .001 of a percent
						*/
						if(loop_counter.show_update()){
							String output_string = loop_counter.update_to_show();
							if(output_string.length() > 3){
								output_string += " || " + output.get_vertex_set_values();
							}
							System.out.printf("%s", output_string);
						}
						if (output.is_valid()){
							/*
								We've found a valid graph! woo hoo!
							*/
							System.out.printf("%nIterations tried: %s", loop_counter.get_counter().toString());
							/*
								Push the vertex set to a csv file that gephi can import
							*/
							try{
								File vertex_set_file = new File(String.format("%s_Vertex_Set.csv", output_path));
								vertex_set_file.createNewFile();
								try(FileWriter output_vertex_set_stream = new FileWriter(String.format("%s_Vertex_Set.csv", output_path))){
									output_vertex_set_stream.write(output.get_vertex_set_CSV());
									output_vertex_set_stream.close();
								}catch(java.io.FileNotFoundException e){
									System.out.println("Printing stack trace... (couldn't find the csv file for vertexes)");
								}
							}catch(IOException e){
								System.out.println("Printing stack trace... (problem creating the csv file for vertexes)");
								e.printStackTrace();
							}
							/*
								Push the edge set to a csv file that gephi can import
							*/
							try{
								File edge_set_file = new File(String.format("%s_Edge_Set.csv", output_path));
								edge_set_file.createNewFile();
								try(FileWriter output_edge_set_stream = new FileWriter(String.format("%s_Edge_Set.csv", output_path))){
									output_edge_set_stream.write(output.get_edge_set_CSV());
									output_edge_set_stream.close();
								}catch(java.io.FileNotFoundException e){
									System.out.println("Printing stack trace... (couldn't find the csv file for edges)");
								}
							}catch(IOException e){
								System.out.println("Printing stack trace... (problem creating the csv file for edges)");
								e.printStackTrace();
							}
							/*
								NOW WE GET TO STOP!
							*/
							input_thread.stop();
						}
						/*
							Now we delete the old permutation, and begin working towards the next iteration. It's a formality, however, that we have to first remove the fixed labeling value off of the end of the list
						*/
						current_working_list.remove(current_working_list.size()-1);
						previous_valid_permutation = new ArrayList<>(current_working_list);
						return output;
					}
				}else{
					/*
						we need to determine the next element we're going to use
					*/
					for(int i = 0; i < array_to_permute.length; i++){
						if(used_indexes[i] || i > 0 && array_to_permute[i] == array_to_permute[i-1] && !used_indexes[i-1]){
							/*
								this is the case where an element is already used, or not valid to use
							*/
							continue;
						}
						/*
							we're good to use the ith index of array_to_permute in our next permutation (:
						*/
						used_indexes[i] = true;
						current_working_list.add(array_to_permute[i]);
						/*
							now we go pick the remaining elements, until we get enough to be another candidate for a unique permutation!
						*/
						test_all_labelings(previous_valid_permutation, current_working_list, array_to_permute, used_indexes, number_to_use, input_edge_relation, input_modulo, input_fixed_labeling, loop_counter, output_path, input_thread);
						/*
							the above line keeps repeating (via the recursion) until all possible permutations with the given element have been listed, so we move on to the next element
						*/
						used_indexes[i] = false;
						current_working_list.remove(current_working_list.size()-1);
					}
				}
		return output;
	}
}