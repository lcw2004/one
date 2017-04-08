package com.lcw.one.workflow.dao;

import com.lcw.one.workflow.bean.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}