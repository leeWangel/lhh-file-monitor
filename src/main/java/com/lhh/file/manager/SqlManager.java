package com.lhh.file.manager;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lhh.file.model.Logs;
import com.lhh.file.utils.UtilsDate;

@Repository("sqlManager")
public class SqlManager {
	private static final Logger log = LoggerFactory.getLogger(SqlManager.class);

    @Resource private SessionFactory sessionFactory;
    
	public void insertLog(Logs logs){
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO logs ");
		sb.append("(idStr, f_type_ot, f_type_ft, content, insert_time, use_user) ");
		sb.append("values ");
		sb.append("(");
		sb.append("'").append(logs.getIdStr()).append("',");
		sb.append("'").append(logs.getF_type_ot()).append("',");
		sb.append("'").append(logs.getF_type_ft()).append("',");
		sb.append("'").append(logs.getContent()).append("',");
		sb.append("'").append(UtilsDate.getDateToString(logs.getInsert_time(), UtilsDate.yyyy_MM_dd_HH_mm_ss)).append("',");
		sb.append("'").append(logs.getUse_user()).append("'");
		
		sb.append(")");
		
		log.info(sb.toString());
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlquery = session.createSQLQuery(sb.toString());
		try {
			sqlquery.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
