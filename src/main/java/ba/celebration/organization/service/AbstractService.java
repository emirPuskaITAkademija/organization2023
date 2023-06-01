package ba.celebration.organization.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

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

    public E find(Object id){
        return entityManager().find(entityClass, id);
    }

    public List<E> findAll(){
        CriteriaQuery criteriaQuery = entityManager().getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return entityManager().createQuery(criteriaQuery).getResultList();
    }
}
