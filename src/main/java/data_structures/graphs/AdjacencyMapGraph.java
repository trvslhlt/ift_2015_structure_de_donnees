package data_structures.graphs;

import java.util.HashMap;
import java.util.Map;

import ca.umontreal.IFT2015.adt.list.LinkedPositionalList;
import ca.umontreal.IFT2015.adt.list.Position;
import ca.umontreal.IFT2015.adt.list.PositionalList;

public class AdjacencyMapGraph<V, E> implements Graph<V, E> {
	
	private boolean isDirected;
	private PositionalList<Vertex<V>> vertices = new LinkedPositionalList<>();
	private PositionalList<Edge<E>> edges = new LinkedPositionalList<>();
	
	public AdjacencyMapGraph(boolean isDirected) {
		this.isDirected = isDirected;
	}
	
	private class InnerVertex implements Vertex<V> {
		private V element;
		private Position<Vertex<V>> pos;
		private Map<Vertex<V>, Edge<E>> outgoing, incoming;
		
		private InnerVertex(V element, boolean graphIsDirected) {
			this.element = element;
			this.outgoing = new HashMap<>();
			if (graphIsDirected) {
				incoming = new HashMap<>();
			} else {
				incoming = outgoing;
			}
		}

		@Override
		public V getElement() {
			return this.element;
		}
		
		public boolean validate(Graph<V, E> graph) {
			return (AdjacencyMapGraph.this == graph && this.pos != null);
		}
		
		public void setPosition(Position<Vertex<V>> p) {
			this.pos = p;
		}
		
		public Position<Vertex<V>> getPosition() {
			return this.pos;
		}
		
		public Map<Vertex<V>, Edge<E>> getOutgoing() {
			return this.outgoing;
		}
		
		public Map<Vertex<V>, Edge<E>> getIncoming() {
			return this.incoming;
		}
		
	}
	
	private class InnerEdge implements Edge<E> {
		private E element;
		private Position<Edge<E>> pos;
		private Vertex<V>[] endpoints;
		
		public InnerEdge(Vertex<V> u, Vertex<V> v, E element) {
			this.element = element;
			this.endpoints = (Vertex<V>[]) new Vertex[] {u, v};
		}
		
		@Override
		public E getElement() {
			return this.element;
		}
		
		public Vertex<V>[] getEndpoints() {
			return this.endpoints;
		}
		
		public boolean validate(Graph<V, E> graph) {
			return AdjacencyMapGraph.this == graph && this.pos != null;
		}
		
		public void setPosition(Position<Edge<E>> position) {
			this.pos = position;
		}
		
	}
	
	@Override
	public int numVertices() {
		return this.vertices.size();
	}

	@Override
	public int numEdges() {
		return this.edges.size();
	}

	@Override
	public Iterable<Vertex<V>> vertices() {
		return this.vertices;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		return this.edges;
	}

	@Override
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		InnerVertex vert = this.validate(v);
		return 0;
	}

	@Override
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vertex<V> insertVertex(V element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E element) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
	private InnerVertex validate(Vertex<V> v) {
		if (!(v instanceof InnerVertex))
			throw new IllegalArgumentException("Invalid vertex");
		InnerVertex vert = (InnerVertex) v; // safe cast
		if (!vert.validate(this))
			throw new IllegalArgumentException("Invalid vertex");
		return vert;
	}
}
