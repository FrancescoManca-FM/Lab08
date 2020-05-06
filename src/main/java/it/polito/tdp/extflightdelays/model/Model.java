package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private ExtFlightDelaysDAO dao;
	Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMap;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
		
	}
	
	public void creaGrafo(int x) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		List<Airport> aeroporti = dao.getAeroporti();
		idMap = new HashMap<>();
		for(Airport a : aeroporti) {
			idMap.put(a.getId(), a);
		}
		
		Graphs.addAllVertices(grafo, aeroporti);
		
		List<ConnessioneAeroporti> lista = dao.getArchi(idMap);
		for(ConnessioneAeroporti a : lista) {
			if(a.getDistanzaMedia()>x) {
				if(this.grafo.containsEdge(a.getArrivo(), a.getPartenza())) {
					
					DefaultWeightedEdge e = this.grafo.getEdge(a.getArrivo(), a.getPartenza());
					this.grafo.setEdgeWeight(e, (a.getDistanzaMedia()+this.grafo.getEdgeWeight(e))/2);
					
				}else {
					Graphs.addEdge(this.grafo, a.getPartenza(), a.getArrivo(), a.getDistanzaMedia());
				}
			}
		}
		
		
	}

	public int nVertici() {
		return grafo.vertexSet().size();
	}
	
	public List<ConnessioneAeroporti> getArchi(){
		return this.dao.getArchi(idMap);
	}

	public int nArchi() {
		return grafo.edgeSet().size();
	}

}
