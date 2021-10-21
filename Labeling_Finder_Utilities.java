import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.BigInteger;
import java.math.*;
import java.util.Locale;
import java.text.NumberFormat;

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

	this function needs:
		import java.math.*;
		import java.util.Locale;
		import java.text.NumberFormat;
*/
	public static Graph test_all_labelings(List<Integer> previous_valid_permutation, List<Integer> current_working_list, int[] array_to_permute, boolean[] used_indexes, int number_to_use, Edge_Relation input_edge_relation, int input_modulo, Big_Integer_Counter loop_counter){
		Graph output = new Graph();
		if(current_working_list.size() == number_to_use){
			/*
				We've gathered enough elements to try and permute the things, first make sure it's not a duplicate
			*/
			if(!current_working_list.equals(previous_valid_permutation)){
				/*
					well, it's not a duplicate, so we can move on by making a new array that we will call our test clipped things and stuff
				*/
				int[] clipped_array_to_permute = new int[current_working_list.size()];
				for(int i = 0; i < clipped_array_to_permute.length; i++){
					clipped_array_to_permute[i] = current_working_list.get(i);
				}
				/*
					at this point, we have gathered enough elements to fill up all the vertexes, so we need to check the permutations of this array, so we do that with the help of the above function
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
					System.out.printf("Iterations tried: %s%n", loop_counter.get_counter().toString());
					System.out.printf("Valid graph found:%n%s%n", output.get_Vertex_Set_details());
					/*
						NOW WE GET TO STOP!
					*/
					System.exit(1);
				}
				/*
					Now we delete the old permutation, and begin working towards the next iteration
				*/
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
				test_all_labelings(previous_valid_permutation, current_working_list, array_to_permute, used_indexes, number_to_use, input_edge_relation, input_modulo, loop_counter);
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