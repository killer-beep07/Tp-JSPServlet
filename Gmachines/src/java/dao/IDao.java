

package dao;


import java.util.List;


public interface IDao <T>  {
    boolean create(T o) ;
    boolean update(T o) ;
    boolean update (T o,T p);
    boolean delete(T o) ;
    T findById(int id) ;
    T findByCode(String code) ;
    List<T> findAll() ;
    
}

