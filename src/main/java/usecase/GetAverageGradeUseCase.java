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
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;

        // Retrieve the team members
        final Team team = gradeDataBase.getMyTeam();
        String[] members = team.getMembers();

        // For each member, get their grade for the course
        for (String member : members) {
            Grade[] grades = gradeDataBase.getGrades(member);

            // Look for the grade in the specified course
            for (Grade grade : grades) {
                if (grade.getCourse().equals(course)) {
                    sum += grade.getGrade();
                    count++;
                    break;
                }
            }
        }

        // If no grades were found, return 0
        if (count == 0) {
            return 0;
        }

        // Return the average grade
        return sum / count;
    }
}
