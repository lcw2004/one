package com.lcw.one.util.persistence;

import com.lcw.one.util.utils.CollectionUtils;
import com.lcw.one.util.utils.PersistenceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @version 2017-05-19.
 * @auth Licw
 */
public class RepositoryUtils {

    private static final Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);

    public static Query getQueryWithParams(EntityManager entityManager, String hql, Object... params) {
        logger.info("HQL [{}]", hql);
        if (params != null && params.length > 0) {
            logger.info("HQL Param: {}", Arrays.asList(params));
            logger.info("HQL Filled: [{}]", PersistenceUtils.fillParams(hql, params));
        }

        Query query = entityManager.createQuery(hql);
        int index = 1;
        for (Object param : params) {
            query.setParameter(index, param);
            index++;
        }
        return query;
    }

    public static Query getQueryWithParamMap(EntityManager entityManager, String hql, Map<String, Object> params) {
        logger.info("HQL [{}]", hql);
        if (CollectionUtils.isNotEmpty(params)) {
            logger.info("HQL Param: {}", params);
            logger.info("HQL Filled: [{}]", PersistenceUtils.fillParams(hql, params));
        }

        Query query = entityManager.createQuery(hql);
        if (CollectionUtils.isNotEmpty(params)) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                query.setParameter(key, value);
            }
        }
        return query;
    }

    public static String buildCountHQL(String hql) {
        StringBuilder countHql = new StringBuilder();
        if (hql.trim().startsWith("select")) {
            int fromIndex = hql.indexOf("from");
            int selectIndex = hql.indexOf("select");
            String hqlBetweenSelectAndForm = hql.substring(selectIndex + 6, fromIndex);
            if (hqlBetweenSelectAndForm.indexOf("new") >= 0 || hqlBetweenSelectAndForm.indexOf("NEW") >= 0) {
                // 如果包含new，用1替换调
                hqlBetweenSelectAndForm = "1";
            }
            String hqlNoSelect = hql.substring(fromIndex, hql.length());
            countHql.append(" select count(").append(hqlBetweenSelectAndForm).append(") ").append(hqlNoSelect);
        } else if (hql.trim().startsWith("from")) {
            countHql.append(" select count(*) ").append(hql);
        }
        return countHql.toString();
    }

    public static <T> T getFirst(Query query) {
        T obj = null;
        List<T> resultList = query.getResultList();
        if (CollectionUtils.isNotEmpty(resultList)) {
            obj = resultList.get(0);
        }
        return obj;
    }

}
