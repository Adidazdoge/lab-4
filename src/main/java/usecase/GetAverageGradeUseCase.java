package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        float sum = 0;
        int count = 0;
        final Team team = gradeDataBase.getMyTeam();
        String[] members = team.getMembers();
        for (String member : members) {
            Grade[] grades = gradeDataBase.getGrades(member);
            for (Grade grade : grades) {
                if (grade.getCourse().equals(course)) {
                    sum += grade.getGrade();
                    count++;
                    break;
                }
            }
        }
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
