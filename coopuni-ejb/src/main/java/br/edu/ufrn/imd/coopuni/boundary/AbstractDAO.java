package br.edu.ufrn.imd.coopuni.boundary;

public interface AbstractDAO<K, E> {
  void create(E entity);

  void delete(K id);

  void retrieve(K id);

  void update(E entity);
}
