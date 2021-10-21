import java.util.Arrays;
public class Graph{
	private boolean valid;
	private int number_of_edges;
	private int number_of_vertexes;
	private Edge_Set edges;
	private Vertex_Set vertexes;
	private int iterations;
/*
	setters and getters
*/
	public int get_iterations(){
		return iterations;
	}
	public boolean is_valid(){
		return valid;
	}
	public int get_size(){
		return number_of_edges;
	}
	public int get_order(){
		return number_of_vertexes;
	}
	public String get_Edge_Set_details(){
		return edges.get_details();
	}
	public String get_Vertex_Set_details(){
		return vertexes.get_details();
	}
	public String get_vertex_set_values(){
		return vertexes.list_vertex_labels();
	}
/*
	Here is a constructor for a graph that only needs a vertex set (that already has values), and an edge relation
*/
	public Graph(Vertex_Set input_vertex_set, Edge_Relation input_edge_relation, int input_modulo){
		number_of_edges = input_edge_relation.get_number_of_edges();
		number_of_vertexes = input_vertex_set.get_number_of_vertexes();
		vertexes = input_vertex_set;
		edges = new Edge_Set(vertexes, input_edge_relation, input_modulo);
		valid = determine_valid();
	}
/*
	Here is the empty constructor
*/
	public Graph(){
		number_of_edges = 0;
		number_of_vertexes = 0;
		vertexes = new Vertex_Set();
		edges = new Edge_Set();
		valid = false;
	}
	public Graph(int iterations){
		this();
		this.iterations = iterations;
	}
/*
	Here is our function that determines if the labeling is valid
*/
	private boolean determine_valid(){
		/*
			this function needs the following include:

			import java.util.Arrays;
		*/
		boolean output = true;
		int[] edge_value_array = edges.get_edge_value_array();
		Arrays.sort(edge_value_array);
		for(int i = 0; i < edge_value_array.length -1; i++){
			if(edge_value_array[i] == edge_value_array[i+1]){
				return false;
			}
		}
		return output;
	}

/*
	Here's the get_details() function
*/
	public String get_details(){
		String output = "";
		output += String.format("Is this valid? %s%nEdge Set:%n%s%nVertex Set:%n%s%nEdge values: ", String.valueOf(is_valid()), edges.get_details(), vertexes.get_details());
		for(int i = 0; i < edges.get_number_of_edges(); i++){
			output += String.format("%d ", edges.get_value_of_edge_at_index(i));
			if(i < edges.get_number_of_edges()-1){
				output += ", ";
			}
		}
		output += "\n";
		return output;
	}
}