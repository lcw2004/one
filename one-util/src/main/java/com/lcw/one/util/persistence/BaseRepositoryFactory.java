package com.lcw.one.util.persistence;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 参考资料：
 * https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-adding-custom-methods-into-all-repositories/
 * http://karaf.922171.n3.nabble.com/file/n4031383/JeetJpaRepositoryFactoryBean.java
 * http://thread.gmane.org/gmane.comp.apache.karaf.user/6389
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 * http://docs.oracle.com/javaee/6/tutorial/doc/gjivm.html
 * http://docs.oracle.com/javaee/6/tutorial/doc/bnbtl.html#bnbtn
 *
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {

    private EntityManager entityManager;

    public BaseRepositoryFactory(EntityManager em) {
        super(em);
        this.entityManager = em;
    }

    @Override
    protected SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
        return new BaseRepositoryImpl<>(entityInformation, entityManager);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return IBaseRepository.class;
    }
}
