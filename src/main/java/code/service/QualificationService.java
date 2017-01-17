package code.service;

import code.domain.Qualification;

import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
public interface QualificationService {
    List getAllQualifications();
    Qualification getQualificationById(Long qualificationId);
}
