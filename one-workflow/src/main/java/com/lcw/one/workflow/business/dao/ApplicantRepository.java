package com.lcw.one.workflow.business.dao;

import com.lcw.one.workflow.business.bean.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}