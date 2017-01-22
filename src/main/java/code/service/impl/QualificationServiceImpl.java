package code.service.impl;

import code.dao.QualificationDao;
import code.domain.Qualification;
import code.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
@Service
@Transactional
public class QualificationServiceImpl implements QualificationService {
    @Autowired(required = true)
    private QualificationDao qualificationDao;


    public List getAllQualifications() {
        return qualificationDao.getAllQualifications();
    }

    public Qualification getQualificationById(Long qualificationId) {
        return qualificationDao.read(qualificationId);
    }
}
