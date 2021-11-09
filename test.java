public class test{

	public static void main(String args[]){
		String graph_to_label = "K_4 Snake";
		Labeling_Finder_Data listing = new Labeling_Finder_Data();
		int[] labeling_set = listing.get_labeling_set(graph_to_label);
		int number_of_vertexes = listing.get_number_of_vertexes_in(graph_to_label);
		Edge_Relation relation_to_use = listing.get_edge_relation(graph_to_label);
		int modulo = labeling_set[labeling_set.length-1]+1;
		Vertex_Set thingie = new Vertex_Set(labeling_set);
		Graph K_4_Snake = new Graph(thingie, relation_to_use, modulo);

		System.out.printf("%s", K_4_Snake.get_vertex_set_CSV());
	}
}