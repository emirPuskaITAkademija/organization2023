package ba.celebration.organization.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractService<E>{

    private Class<E>  entityClass;

    public AbstractService(Class<E>  entityClass){
        this.entityClass = entityClass;
    }

    protected abstract EntityManager entityManager();

    public void create(E entity){
        entityManager().persist(entity);
    }

    public void edit(E entity){
        entityManager().merge(entity);
    }

    public void remove(E entity){
        EntityManager entityManager = entityManager();
        if(!entityManager.contains(entity)){
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
    }

    public void remove(Long id){
        E entity = entityManager().find(entityClass, id);
        if(entity!=null){
            remove(entity);
        }
    }

    public E find(Object id){
        return entityManager().find(entityClass, id);
    }

    public List<E> findAll(){
        CriteriaQuery criteriaQuery = entityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return entityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<E> findPage(int page, int pageSize){
        //Create a CriteriaQueryBuilder
        CriteriaBuilder builder = entityManager().getCriteriaBuilder();
        //Create a CriteriaQuery
        CriteriaQuery<E> criteriaQuery = builder.createQuery(entityClass);
        //Na koju tabelu se kačiš Town.class
        Root<E> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);
        //page=1  page-1
        int firstResult = (page-1)*pageSize;
        Order ascOrder = builder.asc(root.get("id"));
        criteriaQuery.orderBy(ascOrder);
        //SELECT * FROm town LIMIT offset,pageSize ORDER BY id
        return entityManager()
                .createQuery(criteriaQuery)
                .setFirstResult(firstResult)//5
                .setMaxResults(pageSize)
                .getResultList();
    }

    //SELECT COUNT(id) AS count FROM town;
    public int count() {
        //Create a CriteriaQueryBuilder
        CriteriaBuilder builder = entityManager().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery();
        //kaži na koju tabelu/entityClass
        Root<E> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(builder.count(root));
        Query query = entityManager().createQuery(criteriaQuery);
        return ((Long) query.getSingleResult()).intValue();
    }
}
