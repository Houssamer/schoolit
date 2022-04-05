package com.schoolit.schoolit.services.cours;

import com.schoolit.schoolit.repos.CoursRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoursService implements ICoursService {
    private final CoursRepo coursRepo;

    @Autowired
    public CoursService(CoursRepo coursRepo) {
        this.coursRepo = coursRepo;
    }
}
