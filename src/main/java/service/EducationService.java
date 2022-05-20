package service;

import dao.EducationDao;
import entity.Education;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EducationService {
    private EducationDao educationDao;

    public List<Education> getAll(){
        return educationDao.getAll();
    }
    public Education getItem(long id) {
        return educationDao.get(id).orElseThrow();
    }

    public void createEducation(Education education) {
        educationDao.create(education);
    }

    public void editEducation(long id, Education education) {
        education.setId(id);
        educationDao.update(education);
    }

    public void deleteEducation(Education education) {
        educationDao.delete(education);
    }
}
