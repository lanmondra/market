package cat.owc.ms.reports.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import cat.owc.ms.reports.ReportsClass;

@SuppressWarnings("deprecation")
public class AbstractRepository<T, ID>  extends ReportsClass {

    @PersistenceContext
    private EntityManager entityManager;

    protected Object uniqueResult(String sql) {
        return entityManager.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class).uniqueResult();
    }

	protected Object uniqueDTO(String sql, Class<?> clazz) {
        return entityManager.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class).setResultTransformer(Transformers.aliasToBean(clazz)).uniqueResult();
    }

    
    protected <K> List<K> listDTO(String sql, Class<K> clazz) {
        return  entityManager.createNativeQuery(sql).unwrap(Query.class).setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }


    protected List<?> listResult(String sql) {
        return entityManager.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class).list();
    }

    protected List<?> listDTO(String sql, Class<?> clazz, Integer limit) {
        //return entityManager.createNativeQuery(sql).setMaxResults(limit).getResultList();
        return entityManager.createNativeQuery(sql).setMaxResults(limit).unwrap(org.hibernate.query.Query.class).setResultTransformer(Transformers.aliasToBean(clazz)).list();
    }
    
}
