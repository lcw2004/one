package ${packageName};

import ${beanClassName};
import com.lcw.one.util.persistence.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ${genClassSimpleName} extends BaseRepositoryImpl<${beanSimpleName}, ${beanIdType}> {

    @Autowired
    public ${genClassSimpleName}(EntityManager entityManager) {
        super(JpaEntityInformationSupport.getEntityInformation(${beanSimpleName}.class, entityManager), entityManager);
    }

}